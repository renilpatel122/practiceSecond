package com.food.order.service.impl;

import com.food.order.dto.OrderDTO;
import com.food.order.dto.OrderItemDTO;
import com.food.order.entity.Order;
import com.food.order.entity.OrderItem;
import com.food.order.repository.OrderRepository;
import com.food.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDTO placeOrder(OrderDTO dto) {
        Order order = Order.builder()
                .userId(dto.getUserId())
                .restaurantId(dto.getRestaurantId())
                .status("PENDING")
                .orderTime(LocalDateTime.now())
                .build();

        var orderItems = dto.getItems().stream()
                .map(i -> OrderItem.builder()
                        .itemName(i.getItemName())
                        .price(i.getPrice())
                        .quantity(i.getQuantity())
                        .order(order)
                        .build()).collect(Collectors.toList());

        order.setItems(orderItems);

        Order saved = orderRepository.save(order);
        dto.setId(saved.getId());
        dto.setStatus(saved.getStatus());
        dto.setOrderTime(saved.getOrderTime());
        return dto;
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId).map(order -> OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .status(order.getStatus())
                .orderTime(order.getOrderTime())
                .items(order.getItems().stream().map(i -> OrderItemDTO.builder()
                        .itemName(i.getItemName())
                        .price(i.getPrice())
                        .quantity(i.getQuantity())
                        .build()).toList())
                .build()).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
