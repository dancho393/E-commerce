package com.projects.ecommerce.api.model.user.login;

import com.projects.ecommerce.api.model.base.OperationResponse;

public record LoginResponse(
        String jwtToken
) implements OperationResponse {
}
