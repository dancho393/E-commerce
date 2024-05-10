package com.projects.ecommerce.api.model.weborderquantity.create;

import com.projects.ecommerce.api.model.base.OperationRequest;
import com.projects.ecommerce.model.WebOrder;

public record CreateWebOrderQuantityRequest(
        Long productId,
        Integer quantity,
        WebOrder webOrder
) implements OperationRequest {
}
