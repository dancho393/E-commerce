package com.projects.ecommerce.api.model.address.create;

import com.projects.ecommerce.api.model.base.OperationResponse;

public record CreateAddressResponse(
        String message
) implements OperationResponse {
}
