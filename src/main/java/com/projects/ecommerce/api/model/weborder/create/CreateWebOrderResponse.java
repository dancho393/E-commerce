package com.projects.ecommerce.api.model.weborder.create;

import com.projects.ecommerce.api.model.base.OperationResponse;

public record CreateWebOrderResponse(
        String message
) implements OperationResponse {
}
