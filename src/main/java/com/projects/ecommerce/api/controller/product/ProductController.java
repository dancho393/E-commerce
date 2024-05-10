package com.projects.ecommerce.api.controller.product;

import com.projects.ecommerce.api.model.product.create.CreateProductOperation;
import com.projects.ecommerce.api.model.product.create.CreateProductRequest;
import com.projects.ecommerce.api.model.product.create.CreateProductResponse;
import com.projects.ecommerce.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductOperation createProductOperation;

    @GetMapping
    public List<Product> getAllProducts() {
        return null;
    }
    @PostMapping
    public ResponseEntity<CreateProductResponse> getProductById(@RequestBody CreateProductRequest request) {

        return ResponseEntity.ok(createProductOperation.process(request));
    }
}
