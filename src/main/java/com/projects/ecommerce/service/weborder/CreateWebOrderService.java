package com.projects.ecommerce.service.weborder;

import com.projects.ecommerce.api.model.address.findbyid.FindAddressByIdOperation;
import com.projects.ecommerce.api.model.address.findbyid.FindAddressByIdRequest;
import com.projects.ecommerce.api.model.weborder.create.CreateWebOrderOperation;
import com.projects.ecommerce.api.model.weborder.create.CreateWebOrderRequest;
import com.projects.ecommerce.api.model.weborder.create.CreateWebOrderResponse;
import com.projects.ecommerce.api.model.weborderquantity.create.CreateWebOrderQuantityOperation;
import com.projects.ecommerce.api.model.weborderquantity.create.CreateWebOrderQuantityRequest;
import com.projects.ecommerce.model.Address;
import com.projects.ecommerce.model.WebOrder;
import com.projects.ecommerce.model.WebOrderQuantities;
import com.projects.ecommerce.model.repository.WebOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreateWebOrderService implements CreateWebOrderOperation {
    private final WebOrderRepository webOrderRepository;
    private final FindAddressByIdOperation findAddressById;
    private final CreateWebOrderQuantityOperation createWebOrderQuantity;


    @Override
    @Transactional
    public CreateWebOrderResponse process(CreateWebOrderRequest request) {
        WebOrder webOrder = new WebOrder();
        webOrder.setUser(request.getUser());
        webOrder.setAddress(
                getOrderAddress(
                        request.getAddressId()));
        webOrderRepository.save(webOrder);
        webOrder.setQuantities(createWebOrderQuantities(request.getQuantityProductMap(),webOrder));
        return null;
    }
    private Address getOrderAddress(String addressId){
        FindAddressByIdRequest request = new FindAddressByIdRequest(Long.parseLong(addressId));
        return findAddressById.process(request).address();
    }
    private List<WebOrderQuantities> createWebOrderQuantities(
            Map<Long,Integer> quantityProductMap,
            WebOrder webOrder){
        List<WebOrderQuantities> webOrderQuantities= new ArrayList<>();
        for(Map.Entry<Long,Integer> entry: quantityProductMap.entrySet()){

            webOrderQuantities.add(
                    createWebOrderQuantity.process(
                            buildWebOrderQuantityRequest(
                                    entry.getKey(), entry.getValue(), webOrder)
                    ).webOrderQuantities());
        }
        return webOrderQuantities;
    }
    private CreateWebOrderQuantityRequest
    buildWebOrderQuantityRequest(Long productId,
                                 Integer quantity,
                                 WebOrder webOrder){
        return new CreateWebOrderQuantityRequest(productId,quantity,webOrder);
    }
}
