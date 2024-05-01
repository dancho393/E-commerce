package com.projects.ecommerce.model.repository;


import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {
    List<WebOrder> findAllByUser(User user);
}
