package com.projects.ecommerce.api.model.user.changepassword;

import com.projects.ecommerce.api.model.base.OperationRequest;
import com.projects.ecommerce.model.User;

public record ChangeUserPasswordRequest(
        String newPassword,
        User user
) implements OperationRequest {
}
