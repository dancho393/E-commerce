package com.projects.ecommerce.api.controller.auth;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.passwordresettoekn.changepassword.ChangePasswordByTokenOperation;
import com.projects.ecommerce.api.model.passwordresettoekn.changepassword.ChangePasswordByTokenRequest;
import com.projects.ecommerce.api.model.passwordresettoekn.changepassword.ChangePasswordByTokenResponse;
import com.projects.ecommerce.api.model.passwordresettoekn.create.CreatePasswordResetTokenOperation;

import com.projects.ecommerce.api.model.passwordresettoekn.create.CreatePasswordResetTokenRequest;
import com.projects.ecommerce.api.model.passwordresettoekn.create.CreatePasswordResetTokenResponse;
import com.projects.ecommerce.api.model.user.changepassword.ChangeUserPasswordRequest;
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
import lombok.Getter;
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
    private final ChangePasswordByTokenOperation changePasswordByTokenOperation;
    private final CreatePasswordResetTokenOperation createPasswordResetTokenOperation;

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
    @PostMapping("/reset-password")
    public ResponseEntity<CreatePasswordResetTokenResponse> resetPassword(@RequestParam String email){
        CreatePasswordResetTokenRequest request = new CreatePasswordResetTokenRequest(email);
        return ResponseEntity.ok(createPasswordResetTokenOperation.process(request));
    }
    @PatchMapping("/change-password")
    public ResponseEntity<ChangePasswordByTokenResponse> changePassword(
            @RequestParam String token,
            @RequestBody ChangePasswordByTokenRequest request){
        request.setToken(token);

        return ResponseEntity.ok(changePasswordByTokenOperation.process(request));
    }

}
