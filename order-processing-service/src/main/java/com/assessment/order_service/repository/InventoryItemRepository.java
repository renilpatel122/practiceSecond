package com.assessment.order_service.repository;

import com.assessment.order_service.model.InventoryItem;
import com.assessment.order_service.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByMenuItem(MenuItem menuItem);
}
