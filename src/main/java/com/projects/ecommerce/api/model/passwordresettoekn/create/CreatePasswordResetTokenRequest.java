package com.projects.ecommerce.api.model.passwordresettoekn.create;

import com.projects.ecommerce.api.model.base.OperationRequest;
import jakarta.validation.constraints.Email;

public record CreatePasswordResetTokenRequest(
        @Email String email
) implements OperationRequest {
}
