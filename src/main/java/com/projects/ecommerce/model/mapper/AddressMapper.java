package com.projects.ecommerce.model.mapper;

import com.projects.ecommerce.api.model.address.CreateAddressBody;
import com.projects.ecommerce.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user",ignore = true)
    Address bodyToAddress(CreateAddressBody createAddressBody);
}
