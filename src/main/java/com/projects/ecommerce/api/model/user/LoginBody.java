package com.projects.ecommerce.api.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBody {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
