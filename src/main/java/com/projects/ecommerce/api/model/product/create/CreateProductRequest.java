package com.projects.ecommerce.api.model.product.create;

import com.projects.ecommerce.api.model.base.OperationRequest;

public record CreateProductRequest(
         String name,
         String shortDescription,
         String longDescription,
         Double price
) implements OperationRequest {
}
