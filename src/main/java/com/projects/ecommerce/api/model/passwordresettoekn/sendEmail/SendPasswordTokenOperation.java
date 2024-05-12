package com.projects.ecommerce.api.model.passwordresettoekn.sendEmail;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.base.Operation;

public interface SendPasswordTokenOperation extends Operation<SendPasswordTokenResponse,SendPasswordTokenRequest> {
    @Override
    SendPasswordTokenResponse process(SendPasswordTokenRequest request);
}
