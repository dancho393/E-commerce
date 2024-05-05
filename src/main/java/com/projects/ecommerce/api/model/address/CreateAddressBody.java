package com.projects.ecommerce.api.model.address;

import com.projects.ecommerce.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressBody {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
}
