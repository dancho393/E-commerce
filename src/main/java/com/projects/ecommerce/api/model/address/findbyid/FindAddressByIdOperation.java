package com.projects.ecommerce.api.model.address.findbyid;

import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.base.Operation;

public interface FindAddressByIdOperation extends Operation<FindAddressByIdResponse,FindAddressByIdRequest> {
    @Override
    FindAddressByIdResponse process(FindAddressByIdRequest request);
}
