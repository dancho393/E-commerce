package com.projects.ecommerce.service.address;

import com.projects.ecommerce.api.model.address.create.CreateAddressOperation;
import com.projects.ecommerce.api.model.address.create.CreateAddressRequest;
import com.projects.ecommerce.api.model.address.create.CreateAddressResponse;
import com.projects.ecommerce.model.Address;
import com.projects.ecommerce.model.mapper.AddressMapper;
import com.projects.ecommerce.model.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateAddressService implements CreateAddressOperation {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    @Override
    public CreateAddressResponse process(CreateAddressRequest request) {
        Address address = addressMapper.bodyToAddress(request);
        addressRepository.save(address);

        return new CreateAddressResponse("Address created successfully");
    }
}
