package com.projects.ecommerce.api.model.passwordresettoekn.changepassword;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.base.Operation;

public interface ChangePasswordByTokenOperation extends Operation<ChangePasswordByTokenResponse,ChangePasswordByTokenRequest> {
    @Override
    ChangePasswordByTokenResponse process(ChangePasswordByTokenRequest request);
}
