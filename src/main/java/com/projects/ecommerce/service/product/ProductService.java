package com.projects.ecommerce.service.product;

import com.projects.ecommerce.api.exception.EntityNotFoundException;
import com.projects.ecommerce.api.model.product.CreateProductBody;
import com.projects.ecommerce.model.Inventory;
import com.projects.ecommerce.model.Product;
import com.projects.ecommerce.model.mapper.ProductMapper;
import com.projects.ecommerce.model.repository.InventoryRepository;
import com.projects.ecommerce.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final InventoryRepository inventoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public void createProduct(CreateProductBody createProductBody) {
        Product product = productMapper.BodyToProduct(createProductBody);
        productRepository.save(product);
        Inventory inventory = Inventory.builder()
                .product(product)
                .build();
        inventoryRepository.save(inventory);
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("Product not found"));
    }
}
