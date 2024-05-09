package com.projects.ecommerce.api.model.user.create;

import com.projects.ecommerce.api.model.base.OperationResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;


public record CreateUserResponse(
       @NonNull @NotBlank String message)
        implements OperationResponse {

}
