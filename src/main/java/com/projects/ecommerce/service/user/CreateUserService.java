package com.projects.ecommerce.service.user;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.user.create.CreateUserOperation;
import com.projects.ecommerce.api.model.user.create.CreateUserRequest;
import com.projects.ecommerce.api.model.user.create.CreateUserResponse;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.VerificationToken;
import com.projects.ecommerce.model.mapper.UserMapper;
import com.projects.ecommerce.model.repository.UserRepository;
import com.projects.ecommerce.model.repository.VerificationTokenRepository;
import com.projects.ecommerce.service.security.JwtService;
import com.projects.ecommerce.service.verificationtoken.VerificationTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserOperation {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationTokenService verificationTokenService;
    @Override
    @Transactional
    public CreateUserResponse process(CreateUserRequest request) throws UserAlreadyExistsException {
        if(userRepository.findByEmailIgnoreCase(request.email()).isPresent()
                || userRepository.findByUsernameIgnoreCase(request.username()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = userMapper.mapRegistrationToUser(request);
        VerificationToken verificationToken = verificationTokenService
                .generateVerificationToken(
                        user,
                        jwtService.generateEmailToken(user.getEmail()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmailVerified(false);

        userRepository.save(user);
        verificationTokenRepository.save(verificationToken);
        emailService.send(verificationToken);


        return new CreateUserResponse("User created successfully, Now Please Verify Your Email");
    }
}
