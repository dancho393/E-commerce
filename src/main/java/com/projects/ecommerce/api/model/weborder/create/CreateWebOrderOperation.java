package com.projects.ecommerce.api.model.weborder.create;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.base.Operation;

public interface CreateWebOrderOperation extends Operation<CreateWebOrderResponse,CreateWebOrderRequest> {
    @Override
    CreateWebOrderResponse process(CreateWebOrderRequest request);
}
