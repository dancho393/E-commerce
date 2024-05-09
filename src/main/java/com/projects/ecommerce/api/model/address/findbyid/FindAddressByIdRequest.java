package com.projects.ecommerce.api.model.address.findbyid;

import com.projects.ecommerce.api.model.base.OperationRequest;

public record FindAddressByIdRequest(
        Long id
) implements OperationRequest {
}
