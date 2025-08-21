package com.food.order.service;

import com.food.order.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long orderId);
}
