package com.projects.ecommerce.api.controller.adress;


import com.projects.ecommerce.api.model.address.create.CreateAddressOperation;
import com.projects.ecommerce.api.model.address.create.CreateAddressRequest;
import com.projects.ecommerce.api.model.address.create.CreateAddressResponse;
import com.projects.ecommerce.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final CreateAddressOperation createAddressOperation;

    @PostMapping
    public ResponseEntity<CreateAddressResponse> addAddress(
            @RequestBody CreateAddressRequest request,
            @AuthenticationPrincipal User user) {
            request.setUser(user);
        return ResponseEntity.ok(createAddressOperation.process(request));
    }
}
