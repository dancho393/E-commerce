package com.projects.ecommerce.api.model.product.create;

import com.projects.ecommerce.api.model.base.Operation;

public interface CreateProductOperation extends Operation<CreateProductResponse, CreateProductRequest> {
    @Override
    CreateProductResponse process(CreateProductRequest request);
}
