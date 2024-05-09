package com.projects.ecommerce.model.mapper;

import com.projects.ecommerce.api.model.user.RegistrationBody;
import com.projects.ecommerce.api.model.user.create.CreateUserRequest;
import com.projects.ecommerce.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore id as it's generated
    @Mapping(target = "addresses", ignore = true) // Ignore addresses for now
    User mapRegistrationToUser(CreateUserRequest createUserRequest);
}
