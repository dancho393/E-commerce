package com.projects.ecommerce.service.weborder;

import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.WebOrder;
import com.projects.ecommerce.model.repository.WebOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebOrderService {
    private final WebOrderRepository webOrderRepository;

    public List<WebOrder> getOrders(User user) {
        return webOrderRepository.findAllByUser(user);
    }
}
