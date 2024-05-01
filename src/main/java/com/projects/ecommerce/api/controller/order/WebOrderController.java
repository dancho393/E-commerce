package com.projects.ecommerce.api.controller.order;

import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.WebOrder;
import com.projects.ecommerce.service.WebOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class WebOrderController {
    private final WebOrderService webOrderService;
    @GetMapping
    public List<WebOrder> getOrders(@AuthenticationPrincipal final User user) {
        return webOrderService.getOrders(user);
    }
}
