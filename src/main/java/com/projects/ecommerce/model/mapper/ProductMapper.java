package com.projects.ecommerce.model.mapper;

import com.projects.ecommerce.api.model.product.CreateProductBody;
import com.projects.ecommerce.api.model.product.create.CreateProductRequest;
import com.projects.ecommerce.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "inventory", ignore = true)
    Product BodyToProduct(CreateProductRequest createProductRequest);
}
