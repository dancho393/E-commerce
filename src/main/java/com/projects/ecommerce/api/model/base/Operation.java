package com.projects.ecommerce.api.model.base;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;

public interface Operation <O extends OperationResponse,I extends OperationRequest>{
    O process(I request) throws UserAlreadyExistsException;
}
