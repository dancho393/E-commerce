package com.projects.ecommerce.api.model.user.verifyemail;

import com.projects.ecommerce.api.model.base.OperationResponse;

public record VerifyEmailResponse(
        String message
) implements OperationResponse {
}
