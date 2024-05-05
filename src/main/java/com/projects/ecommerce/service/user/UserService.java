package com.projects.ecommerce.service.user;

import com.projects.ecommerce.api.exception.AccountNotVerifiedException;
import com.projects.ecommerce.api.exception.TokenNotFound;
import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.exception.UserNotFoundException;
import com.projects.ecommerce.api.model.user.LoginBody;
import com.projects.ecommerce.api.model.user.RegistrationBody;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.VerificationToken;
import com.projects.ecommerce.model.mapper.UserMapper;
import com.projects.ecommerce.model.repository.UserRepository;

import com.projects.ecommerce.model.repository.VerificationTokenRepository;
import com.projects.ecommerce.service.security.JwtService;
import com.projects.ecommerce.service.verificationtoken.VerificationTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public String register( RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if(userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = userMapper.mapRegistrationToUser(registrationBody);
        VerificationToken verificationToken = verificationTokenService
                .generateVerificationToken(
                        user,
                        jwtService.generateEmailToken(user.getEmail()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmailVerified(false);

        userRepository.save(user);
        verificationTokenRepository.save(verificationToken);
        emailService.send(verificationToken);

        System.out.println("Token In The UserService:"+verificationToken.getToken());
        return "Please Verify Your Account To Activate It";
    }
    @Transactional
    public String login(LoginBody loginBody){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginBody.getUsername(),
                        loginBody.getPassword()
                )
        );
        var user = userRepository.findByUsernameIgnoreCase(loginBody.getUsername())
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        if(!user.isEnabled())
            throw new AccountNotVerifiedException("Please Verify Your Account First(Email)");
        return jwtService.generateToken(user);
    }
    @Transactional
    public String verifyEmail(String token){


        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token)
                .orElseThrow(()->  new TokenNotFound("Token Not Found"));
        User user= verificationToken.getUser();
        user.setEmailVerified(true);
        userRepository.save(user);
        return "You Verified Your Email Token";
    }
}
