package com.projects.ecommerce.api.model.address.create;

import com.projects.ecommerce.api.model.base.OperationRequest;
import com.projects.ecommerce.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Getter
@Setter
public class CreateAddressRequest implements OperationRequest {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private User user;
}
