package com.projects.ecommerce.api.model.user.create;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.base.Operation;

public interface CreateUserOperation extends Operation<CreateUserResponse, CreateUserRequest> {
    @Override
    CreateUserResponse process(CreateUserRequest request) throws UserAlreadyExistsException;
}
