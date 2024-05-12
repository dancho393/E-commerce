package com.projects.ecommerce.api.model.passwordresettoekn.changepassword;

import com.projects.ecommerce.api.model.base.OperationResponse;

public record ChangePasswordByTokenResponse(
        String message
) implements OperationResponse {
}
