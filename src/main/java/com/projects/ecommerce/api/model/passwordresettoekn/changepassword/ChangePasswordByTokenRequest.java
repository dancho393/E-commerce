package com.projects.ecommerce.api.model.passwordresettoekn.changepassword;

import com.projects.ecommerce.api.model.base.OperationRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordByTokenRequest implements OperationRequest {
    private String token;
    private String newPassword;
}
