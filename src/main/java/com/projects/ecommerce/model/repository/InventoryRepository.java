package com.projects.ecommerce.model.repository;

import com.projects.ecommerce.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository  extends JpaRepository<Inventory, Long> {
}
