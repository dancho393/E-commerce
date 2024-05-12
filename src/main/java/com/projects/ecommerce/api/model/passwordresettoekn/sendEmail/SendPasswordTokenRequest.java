package com.projects.ecommerce.api.model.passwordresettoekn.sendEmail;

import com.projects.ecommerce.api.model.base.OperationRequest;

public record SendPasswordTokenRequest(
        String email,
        String token
) implements OperationRequest {
}
