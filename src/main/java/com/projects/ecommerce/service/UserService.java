package com.projects.ecommerce.service;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.exception.UserNotFoundException;
import com.projects.ecommerce.api.model.user.LoginBody;
import com.projects.ecommerce.api.model.user.RegistrationBody;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.mapper.UserMapper;
import com.projects.ecommerce.model.repository.UserRepository;

import com.projects.ecommerce.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    public String register( RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if(userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = userMapper.mapRegistrationToUser(registrationBody);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return jwtService.generateToken(user);
    }
    public String login(LoginBody loginBody){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginBody.getUsername(),
                        loginBody.getPassword()
                )
        );
        var user = userRepository.findByUsernameIgnoreCase(loginBody.getUsername())
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        return jwtService.generateToken(user);
    }
}
