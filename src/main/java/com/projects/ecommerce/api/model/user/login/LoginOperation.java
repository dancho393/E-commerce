package com.projects.ecommerce.api.model.user.login;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.base.Operation;

public interface LoginOperation extends Operation<LoginResponse,LoginRequest> {
    @Override
    LoginResponse process(LoginRequest request) throws UserAlreadyExistsException;
}
