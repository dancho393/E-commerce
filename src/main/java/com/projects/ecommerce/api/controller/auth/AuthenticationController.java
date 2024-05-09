package com.projects.ecommerce.api.controller.auth;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.user.LoginBody;
import com.projects.ecommerce.api.model.user.RegistrationBody;
import com.projects.ecommerce.api.model.user.create.CreateUserOperation;
import com.projects.ecommerce.api.model.user.create.CreateUserRequest;
import com.projects.ecommerce.api.model.user.create.CreateUserResponse;
import com.projects.ecommerce.api.model.user.login.LoginOperation;
import com.projects.ecommerce.api.model.user.login.LoginRequest;
import com.projects.ecommerce.api.model.user.login.LoginResponse;
import com.projects.ecommerce.api.model.user.verifyemail.VerifyEmailOperation;
import com.projects.ecommerce.api.model.user.verifyemail.VerifyEmailRequest;
import com.projects.ecommerce.api.model.user.verifyemail.VerifyEmailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final CreateUserOperation createUserOperation;
    private final LoginOperation loginOperation;
    private final VerifyEmailOperation verifyEmailOperation;

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> registerUser(@Valid @RequestBody CreateUserRequest request) throws UserAlreadyExistsException {
        try {
            return ResponseEntity.ok(createUserOperation.process(request));
        }
        catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @GetMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request)
            throws UserAlreadyExistsException {
        return ResponseEntity.ok(loginOperation.process(request));
    }
    @PatchMapping("/verify")
    public ResponseEntity<VerifyEmailResponse> verifyEmail(@RequestParam String token){
        VerifyEmailRequest request = new VerifyEmailRequest(token);
        return ResponseEntity.ok(verifyEmailOperation.process(request));
    }

}
