package com.projects.ecommerce.api.controller.auth;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.user.LoginBody;
import com.projects.ecommerce.api.model.user.RegistrationBody;
import com.projects.ecommerce.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationBody registrationBody) throws UserAlreadyExistsException {
        try {
            return ResponseEntity.ok(userService.register(registrationBody));
        }
        catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginBody loginBody)
            throws UserAlreadyExistsException {
        return ResponseEntity.ok(userService.login(loginBody));
    }
    @PatchMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token){
        return ResponseEntity.ok(userService.verifyEmail(token));
    }
}
