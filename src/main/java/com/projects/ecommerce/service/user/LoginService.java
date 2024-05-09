package com.projects.ecommerce.service.user;

import com.projects.ecommerce.api.exception.AccountNotVerifiedException;
import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.exception.UserNotFoundException;
import com.projects.ecommerce.api.model.user.login.LoginOperation;
import com.projects.ecommerce.api.model.user.login.LoginRequest;
import com.projects.ecommerce.api.model.user.login.LoginResponse;
import com.projects.ecommerce.model.repository.UserRepository;
import com.projects.ecommerce.service.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService implements LoginOperation {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Override
    @Transactional
    public LoginResponse process(LoginRequest request) throws UserAlreadyExistsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = userRepository.findByUsernameIgnoreCase(request.username())
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        if(!user.isEnabled())
            throw new AccountNotVerifiedException("Please Verify Your Account First(Email)");
        return new LoginResponse(jwtService.generateToken(user));
    }
}
