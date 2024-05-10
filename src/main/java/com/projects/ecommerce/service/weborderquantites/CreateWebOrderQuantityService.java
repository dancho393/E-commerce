package com.projects.ecommerce.service.weborderquantites;

import com.projects.ecommerce.api.model.product.findById.FindProductByIdOperation;
import com.projects.ecommerce.api.model.product.findById.FindProductByIdRequest;
import com.projects.ecommerce.api.model.weborderquantity.create.CreateWebOrderQuantityOperation;
import com.projects.ecommerce.api.model.weborderquantity.create.CreateWebOrderQuantityRequest;
import com.projects.ecommerce.api.model.weborderquantity.create.CreateWebOrderQuantityResponse;
import com.projects.ecommerce.model.WebOrderQuantities;
import com.projects.ecommerce.model.repository.WebOrderQuantitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateWebOrderQuantityService implements CreateWebOrderQuantityOperation {
    private final WebOrderQuantitiesRepository webOrderQuantitiesRepository;
    private final FindProductByIdOperation findProductByIdOperation;
    @Override
    public CreateWebOrderQuantityResponse process(CreateWebOrderQuantityRequest request) {
        WebOrderQuantities webOrderQuantities = new WebOrderQuantities();
        webOrderQuantities.setProduct(
                findProductByIdOperation.process(
                        createFindProductByIdRequest
                                (request.productId())).product());

        webOrderQuantities.setQuantity(request.quantity());
        webOrderQuantities.setWebOrder(request.webOrder());
        return new CreateWebOrderQuantityResponse(webOrderQuantitiesRepository.save(webOrderQuantities));

    }
    private FindProductByIdRequest createFindProductByIdRequest(Long productId) {
        return new FindProductByIdRequest(productId);
    }
}
