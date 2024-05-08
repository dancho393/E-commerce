package com.projects.ecommerce.service.weborder;


import com.projects.ecommerce.api.model.weborder.CreateWebOrderBody;
import com.projects.ecommerce.model.*;

import com.projects.ecommerce.model.repository.AddressRepository;
import com.projects.ecommerce.model.repository.ProductRepository;
import com.projects.ecommerce.model.repository.WebOrderRepository;
import com.projects.ecommerce.service.address.AddressService;
import com.projects.ecommerce.service.weborderquantites.WebOrderQuantitiesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WebOrderService {
    private final WebOrderRepository webOrderRepository;
    private final AddressService  addressService;
    private final WebOrderQuantitiesService webOrderQuantitiesService;



    public List<WebOrder> getOrders(User user) {
        return webOrderRepository.findAllByUser(user);
    }

    @Transactional
    public String createOrder(CreateWebOrderBody createWebOrderBody,User user){
        WebOrder webOrder = new WebOrder();
        webOrder.setUser(user);
        webOrder.setAddress(
                getOrderAddress(
                        createWebOrderBody.getAddressId()));
        webOrderRepository.save(webOrder);
        webOrder.setQuantities(createWebOrderQuantities(createWebOrderBody.getQuantityProductMap(),webOrder));



        return "Created";
    }
    public Address getOrderAddress(String addressId){
        return addressService.findAddressById(Long.parseLong(addressId));
    }
    public List<WebOrderQuantities> createWebOrderQuantities(
            Map<Long,Integer> quantityProductMap,
            WebOrder webOrder){
        List<WebOrderQuantities> webOrderQuantities= new ArrayList<>();
        for(Map.Entry<Long,Integer> entry: quantityProductMap.entrySet()){
            webOrderQuantities.add(
                    webOrderQuantitiesService.createWebOrderQuantity(entry.getKey(),entry.getValue(),webOrder));
        }
        return webOrderQuantities;
    }


}
