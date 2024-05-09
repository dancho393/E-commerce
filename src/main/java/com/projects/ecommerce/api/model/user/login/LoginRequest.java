package com.projects.ecommerce.api.model.user.login;

import com.projects.ecommerce.api.model.base.OperationRequest;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password

) implements OperationRequest {
}
