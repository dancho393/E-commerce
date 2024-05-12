package com.projects.ecommerce.api.model.passwordresettoekn.sendEmail;

import com.projects.ecommerce.api.model.base.OperationResponse;

public record SendPasswordTokenResponse(
        String message
) implements OperationResponse {
}
