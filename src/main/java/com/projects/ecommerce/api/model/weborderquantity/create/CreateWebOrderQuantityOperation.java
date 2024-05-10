package com.projects.ecommerce.api.model.weborderquantity.create;

import com.projects.ecommerce.api.model.base.Operation;

public interface CreateWebOrderQuantityOperation extends Operation<CreateWebOrderQuantityResponse, CreateWebOrderQuantityRequest> {
    @Override
    CreateWebOrderQuantityResponse process(CreateWebOrderQuantityRequest request);
}
