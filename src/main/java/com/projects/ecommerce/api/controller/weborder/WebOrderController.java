package com.projects.ecommerce.api.controller.weborder;

import com.projects.ecommerce.api.model.weborder.CreateWebOrderBody;
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
    private final WebOrderService webOrderService;

    @GetMapping
    public List<WebOrder> getOrders(@AuthenticationPrincipal final User user) {
        return webOrderService.getOrders(user);
    }
    @PostMapping
    public ResponseEntity<String> createOrder(
            @AuthenticationPrincipal User user,
            @RequestBody CreateWebOrderBody createWebOrderBody
            ){
        return ResponseEntity.ok(webOrderService.createOrder(createWebOrderBody, user));

    }
}
