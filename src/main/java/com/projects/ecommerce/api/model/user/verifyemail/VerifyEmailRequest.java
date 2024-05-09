package com.projects.ecommerce.api.model.user.verifyemail;

import com.projects.ecommerce.api.model.base.OperationRequest;

public record VerifyEmailRequest(
        String token
)
        implements OperationRequest {
}
