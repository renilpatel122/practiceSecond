package com.trading.orderservice.service;

import com.trading.orderservice.dto.OrderRequest;
import com.trading.orderservice.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(OrderRequest request);
    Optional<Order> getOrderById(String orderId);
    List<Order> getOrdersByUserId(String userId);

}
