package com.projects.ecommerce.api.controller.product;

import com.projects.ecommerce.api.model.product.CreateProductBody;
import com.projects.ecommerce.model.Product;
import com.projects.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping
    public ResponseEntity<String> getProductById(@RequestBody CreateProductBody createProductBody) {
        productService.createProduct(createProductBody);
        return ResponseEntity.ok("Created");
    }
}
