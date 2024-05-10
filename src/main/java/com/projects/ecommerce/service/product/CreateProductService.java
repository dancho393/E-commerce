package com.projects.ecommerce.service.product;

import com.projects.ecommerce.api.model.product.create.CreateProductOperation;
import com.projects.ecommerce.api.model.product.create.CreateProductRequest;
import com.projects.ecommerce.api.model.product.create.CreateProductResponse;
import com.projects.ecommerce.model.Inventory;
import com.projects.ecommerce.model.Product;
import com.projects.ecommerce.model.mapper.ProductMapper;
import com.projects.ecommerce.model.repository.InventoryRepository;
import com.projects.ecommerce.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductService implements CreateProductOperation {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final InventoryRepository inventoryRepository;

    @Override
    public CreateProductResponse process(CreateProductRequest request) {
        Product product = productMapper.BodyToProduct(request);
        productRepository.save(product);
        Inventory inventory = Inventory.builder()
                .product(product)
                .build();
        inventoryRepository.save(inventory);
        return new CreateProductResponse("Product");
    }
}
