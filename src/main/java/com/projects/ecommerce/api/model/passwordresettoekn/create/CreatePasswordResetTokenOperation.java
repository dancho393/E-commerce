package com.projects.ecommerce.api.model.passwordresettoekn.create;

import com.projects.ecommerce.api.model.base.Operation;

public interface CreatePasswordResetTokenOperation extends Operation <CreatePasswordResetTokenResponse, CreatePasswordResetTokenRequest>{
    @Override
    public CreatePasswordResetTokenResponse process(CreatePasswordResetTokenRequest request);
}

