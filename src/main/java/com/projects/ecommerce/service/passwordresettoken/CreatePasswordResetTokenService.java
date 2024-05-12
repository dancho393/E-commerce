package com.projects.ecommerce.service.passwordresettoken;

import com.projects.ecommerce.api.exception.EntityNotFoundException;
import com.projects.ecommerce.api.model.passwordresettoekn.create.CreatePasswordResetTokenOperation;
import com.projects.ecommerce.api.model.passwordresettoekn.create.CreatePasswordResetTokenRequest;
import com.projects.ecommerce.api.model.passwordresettoekn.create.CreatePasswordResetTokenResponse;
import com.projects.ecommerce.api.model.passwordresettoekn.sendEmail.SendPasswordTokenOperation;
import com.projects.ecommerce.api.model.passwordresettoekn.sendEmail.SendPasswordTokenRequest;
import com.projects.ecommerce.model.PasswordResetToken;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.repository.PasswordResetTokenRepository;
import com.projects.ecommerce.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePasswordResetTokenService implements CreatePasswordResetTokenOperation {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final SendPasswordTokenOperation sendPasswordTokenOperation;

    @Override
    public CreatePasswordResetTokenResponse process(CreatePasswordResetTokenRequest request) {
        User user=userRepository.findByEmailIgnoreCase(request.email())
                .orElseThrow(()->new EntityNotFoundException("User not found"));
        String token=UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(token);
        passwordResetTokenRepository.save(passwordResetToken);
        String message= sendPasswordTokenOperation.process(
                buildsendPasswordTokenRequest(token, request.email())
        ).message();
        return new CreatePasswordResetTokenResponse(passwordResetToken,message);
    }
    private SendPasswordTokenRequest buildsendPasswordTokenRequest(
            String token,
            String email
    ){
        return new SendPasswordTokenRequest(email,token);
    }
}
