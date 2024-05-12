package com.projects.ecommerce.model.mapper;

import com.projects.ecommerce.api.model.address.create.CreateAddressRequest;
import com.projects.ecommerce.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);
    @Mapping(target = "id",ignore = true)
    Address bodyToAddress(CreateAddressRequest createAddressRequest);
}
