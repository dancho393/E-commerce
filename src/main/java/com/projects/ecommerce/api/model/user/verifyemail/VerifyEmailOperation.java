package com.projects.ecommerce.api.model.user.verifyemail;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.base.Operation;

public interface VerifyEmailOperation extends Operation<VerifyEmailResponse, VerifyEmailRequest> {
    @Override
    VerifyEmailResponse process(VerifyEmailRequest request) ;
}
