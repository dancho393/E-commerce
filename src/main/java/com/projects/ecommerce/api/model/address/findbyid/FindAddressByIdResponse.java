package com.projects.ecommerce.api.model.address.findbyid;

import com.projects.ecommerce.api.model.base.OperationResponse;
import com.projects.ecommerce.model.Address;

public record FindAddressByIdResponse(
        Address address
) implements OperationResponse {

}
