package com.projects.ecommerce.api.model.product.findById;
import com.projects.ecommerce.api.model.base.Operation;

public interface FindProductByIdOperation extends Operation<FindProductByIdResponse, FindProductByIdRequest> {
    @Override
    FindProductByIdResponse process(FindProductByIdRequest request);
}
