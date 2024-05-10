package com.projects.ecommerce.api.model.weborder.create;

import com.projects.ecommerce.api.model.base.OperationRequest;
import com.projects.ecommerce.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreateWebOrderRequest implements OperationRequest {
    private String addressId;
    private Map<Long,Integer> quantityProductMap;
    private User user;
}
