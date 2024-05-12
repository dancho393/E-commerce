package com.projects.ecommerce.api.model.passwordresettoekn.create;

import com.projects.ecommerce.api.model.base.OperationResponse;
import com.projects.ecommerce.model.PasswordResetToken;

public record CreatePasswordResetTokenResponse(
        PasswordResetToken passwordResetToken,
        String message
) implements OperationResponse {

}
