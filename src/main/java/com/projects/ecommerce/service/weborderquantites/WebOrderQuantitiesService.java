package com.projects.ecommerce.service.weborderquantites;

import com.projects.ecommerce.model.WebOrder;
import com.projects.ecommerce.model.WebOrderQuantities;
import com.projects.ecommerce.model.repository.WebOrderQuantitiesRepository;
import com.projects.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebOrderQuantitiesService {
    private final WebOrderQuantitiesRepository webOrderQuantitiesRepository;
    private final ProductService productService;

    public WebOrderQuantities createWebOrderQuantity(Long productId, Integer quantity, WebOrder webOrder){
        WebOrderQuantities webOrderQuantities = new WebOrderQuantities();
        webOrderQuantities.setProduct(productService.getProductById(productId));
        webOrderQuantities.setQuantity(quantity);
        webOrderQuantities.setWebOrder(webOrder);
        return webOrderQuantitiesRepository.save(webOrderQuantities);
    }
}
