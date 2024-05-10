package com.projects.ecommerce.service.product;

import com.projects.ecommerce.api.exception.EntityNotFoundException;
import com.projects.ecommerce.api.model.product.findById.FindProductByIdOperation;
import com.projects.ecommerce.api.model.product.findById.FindProductByIdRequest;
import com.projects.ecommerce.api.model.product.findById.FindProductByIdResponse;
import com.projects.ecommerce.model.Product;
import com.projects.ecommerce.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindProductByIdService implements FindProductByIdOperation {
    private final ProductRepository productRepository;
    @Override
    public FindProductByIdResponse process(FindProductByIdRequest request) {
        Product product = productRepository.findById(request.id())
                .orElseThrow(()->new EntityNotFoundException("Product not found"));
        return new FindProductByIdResponse(product);
    }
}
