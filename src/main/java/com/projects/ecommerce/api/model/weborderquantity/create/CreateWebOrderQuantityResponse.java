package com.projects.ecommerce.api.model.weborderquantity.create;

import com.projects.ecommerce.api.model.base.OperationResponse;
import com.projects.ecommerce.model.WebOrderQuantities;

public record CreateWebOrderQuantityResponse(
        WebOrderQuantities webOrderQuantities
) implements OperationResponse {

}
