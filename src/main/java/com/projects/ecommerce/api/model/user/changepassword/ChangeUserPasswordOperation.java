package com.projects.ecommerce.api.model.user.changepassword;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.base.Operation;

public interface ChangeUserPasswordOperation extends Operation<ChangeUserPasswordResponse,ChangeUserPasswordRequest> {
    @Override
    ChangeUserPasswordResponse process(ChangeUserPasswordRequest request);
}
