package com.projects.ecommerce.api.model.user.changepassword;

import com.projects.ecommerce.api.model.base.OperationResponse;

public record ChangeUserPasswordResponse(
        String newPassword
) implements OperationResponse {
}
