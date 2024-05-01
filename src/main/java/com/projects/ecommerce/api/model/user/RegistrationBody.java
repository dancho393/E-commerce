package com.projects.ecommerce.api.model.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RegistrationBody {
    @NonNull
    @NotBlank
    @Size(min=3, max=255)
    private String username;
    @NonNull
    @NotBlank
    @Size(min=6, max=50)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    @NonNull
    @NotBlank
    private String firstName;
    @NonNull
    @NotBlank
    private String lastName;
    @NonNull
    @NotBlank
    @Email
    private String email;
}
