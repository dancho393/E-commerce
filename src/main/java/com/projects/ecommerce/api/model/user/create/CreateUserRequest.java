package com.projects.ecommerce.api.model.user.create;

import com.projects.ecommerce.api.model.base.OperationRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

public record CreateUserRequest(
        @NonNull @NotBlank @Size(min=3, max=255) String username,
        @NonNull @NotBlank @Size(min=6, max=50) @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") String password,
        @NonNull @NotBlank String firstName,
        @NonNull @NotBlank String lastName,
        @NonNull @NotBlank @Email String email
) implements OperationRequest {}

