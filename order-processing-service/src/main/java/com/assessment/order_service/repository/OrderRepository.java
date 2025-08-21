package com.assessment.order_service.repository;

import com.assessment.order_service.model.Customer;
import com.assessment.order_service.model.Order;
import com.assessment.order_service.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByShop(Shop shop);
}
