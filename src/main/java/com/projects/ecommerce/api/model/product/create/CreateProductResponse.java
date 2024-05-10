package com.projects.ecommerce.api.model.product.create;

import com.projects.ecommerce.api.model.base.OperationResponse;

public record CreateProductResponse(
        String message
) implements OperationResponse {
}
