package com.projects.ecommerce.service.passwordresettoken;

import com.projects.ecommerce.api.exception.EntityNotFoundException;
import com.projects.ecommerce.api.model.passwordresettoekn.changepassword.ChangePasswordByTokenOperation;
import com.projects.ecommerce.api.model.passwordresettoekn.changepassword.ChangePasswordByTokenRequest;
import com.projects.ecommerce.api.model.passwordresettoekn.changepassword.ChangePasswordByTokenResponse;
import com.projects.ecommerce.api.model.user.changepassword.ChangeUserPasswordOperation;
import com.projects.ecommerce.api.model.user.changepassword.ChangeUserPasswordRequest;
import com.projects.ecommerce.model.PasswordResetToken;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordByTokenService implements ChangePasswordByTokenOperation {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final ChangeUserPasswordOperation changeUserPasswordOperation;

    @Override
    public ChangePasswordByTokenResponse process(ChangePasswordByTokenRequest request) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository
                .findByToken(request.getToken())
                .orElseThrow(()->new EntityNotFoundException("Token Not Found"));
        String message=
                changeUserPasswordOperation.process(
                        createChangeUserPasswordRequest(
                                request.getNewPassword(), passwordResetToken.getUser()
                        )).newPassword();

        return new ChangePasswordByTokenResponse(message);
    }
    private ChangeUserPasswordRequest createChangeUserPasswordRequest(String newPassword, User user) {
        return new ChangeUserPasswordRequest(newPassword, user);
    }
}
