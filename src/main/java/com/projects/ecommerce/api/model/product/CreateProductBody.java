package com.projects.ecommerce.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductBody {
    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
}
