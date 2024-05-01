package com.projects.ecommerce.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WebOrderMapper {
    WebOrderMapper INSTANCE = Mappers.getMapper(WebOrderMapper.class);


}
