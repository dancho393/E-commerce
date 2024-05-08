package com.projects.ecommerce.api.model.weborder;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CreateWebOrderBody {
    private String addressId;
    Map<Long,Integer> quantityProductMap;
}
