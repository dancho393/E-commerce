package com.projects.ecommerce.api.model.product.findById;

import com.projects.ecommerce.api.model.base.OperationResponse;
import com.projects.ecommerce.model.Product;

public record FindProductByIdResponse(
        Product product
) implements OperationResponse {
}
