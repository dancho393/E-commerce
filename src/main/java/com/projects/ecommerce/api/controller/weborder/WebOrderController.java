package com.projects.ecommerce.api.controller.weborder;

import com.projects.ecommerce.api.model.weborder.CreateWebOrderBody;
import com.projects.ecommerce.api.model.weborder.create.CreateWebOrderOperation;
import com.projects.ecommerce.api.model.weborder.create.CreateWebOrderRequest;
import com.projects.ecommerce.api.model.weborder.create.CreateWebOrderResponse;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.WebOrder;
import com.projects.ecommerce.service.weborder.WebOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/web-orders")
@RequiredArgsConstructor
public class WebOrderController {
    private final CreateWebOrderOperation createOrder;

    @GetMapping
    public List<WebOrder> getOrders(@AuthenticationPrincipal final User user) {
        return null;
        //return webOrderService.getOrders(user);
    }
    @PostMapping
    public ResponseEntity<CreateWebOrderResponse> createOrder(
            @AuthenticationPrincipal User user,
            @RequestBody CreateWebOrderRequest request
            ){
        request.setUser(user);
        return ResponseEntity.ok(createOrder.process(request));

    }
}
