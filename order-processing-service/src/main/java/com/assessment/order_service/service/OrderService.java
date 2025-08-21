package com.assessment.order_service.service;

import com.assessment.order_service.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO placeOrder(OrderDTO orderDTO);
    List<OrderDTO> getOrdersByCustomer(Long customerId);
    List<OrderDTO> getOrdersByShop(Long shopId);
    OrderDTO completeOrder(Long orderId);

}
