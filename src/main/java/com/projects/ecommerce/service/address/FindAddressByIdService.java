package com.projects.ecommerce.service.address;

import com.projects.ecommerce.api.exception.EntityNotFoundException;
import com.projects.ecommerce.api.model.address.findbyid.FindAddressByIdOperation;
import com.projects.ecommerce.api.model.address.findbyid.FindAddressByIdRequest;
import com.projects.ecommerce.api.model.address.findbyid.FindAddressByIdResponse;
import com.projects.ecommerce.model.Address;
import com.projects.ecommerce.model.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAddressByIdService implements FindAddressByIdOperation {
    private final AddressRepository addressRepository;

    @Override
    public FindAddressByIdResponse process(FindAddressByIdRequest request) {
        Address address=addressRepository.findById(request.id())
                .orElseThrow(()->new EntityNotFoundException("Address Not Found"));
        return new FindAddressByIdResponse( address);
    }
}
