package com.assessment.order_service.repository;

import com.assessment.order_service.model.MenuItem;
import com.assessment.order_service.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByShop(Shop shop);
}
