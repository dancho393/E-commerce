package com.projects.ecommerce.service.address;

import com.projects.ecommerce.api.exception.EntityNotFoundException;
import com.projects.ecommerce.api.model.address.CreateAddressBody;
import com.projects.ecommerce.model.Address;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.mapper.AddressMapper;
import com.projects.ecommerce.model.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    public String createAddress(CreateAddressBody createAddressBody, User user) {
        Address address = addressMapper.bodyToAddress(createAddressBody);
        address.setUser(user);
        addressRepository.save(address);
        
        return "Address created";
    }
    public Address findAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("No address found with id: " + id));
    }
}
