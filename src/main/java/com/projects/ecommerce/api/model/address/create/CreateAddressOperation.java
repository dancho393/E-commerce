package com.projects.ecommerce.api.model.address.create;

import com.projects.ecommerce.api.model.base.Operation;

public interface CreateAddressOperation extends Operation<CreateAddressResponse, CreateAddressRequest> {
    @Override
    CreateAddressResponse process(CreateAddressRequest request);
}
