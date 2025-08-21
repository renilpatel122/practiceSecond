package com.assessment.order_service.repository;

import com.assessment.order_service.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    // Add custom query methods if needed
}
