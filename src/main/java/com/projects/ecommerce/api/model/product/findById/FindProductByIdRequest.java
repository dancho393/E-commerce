package com.projects.ecommerce.api.model.product.findById;

import com.projects.ecommerce.api.model.base.OperationRequest;

public record FindProductByIdRequest(
        Long id
) implements OperationRequest {
}
