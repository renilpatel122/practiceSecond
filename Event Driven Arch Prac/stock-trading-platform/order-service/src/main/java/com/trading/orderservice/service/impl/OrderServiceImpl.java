package com.trading.orderservice.service.impl;

import com.trading.common.constants.KafkaTopics;
import com.trading.common.event.OrderPlacedEvent;
import com.trading.common.exception.ResourceNotFoundException;
import com.trading.orderservice.dto.OrderRequest;
import com.trading.orderservice.entity.Order;
import com.trading.orderservice.repository.OrderRepository;
import com.trading.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        validateOrderRequest(orderRequest);

        Order order = new Order(orderRequest.getUserId(), orderRequest.getStockSymbol(), orderRequest.getQuantity(), orderRequest.getPrice(), orderRequest.getOrderType());
        order.setTimestamp(Instant.now());

        Order savedOrder = orderRepository.save(order);
        log.info("Order saved with id: {}", savedOrder.getOrderId());

        OrderPlacedEvent event = buildOrderPlacedEvent(savedOrder);

        try {
            kafkaTemplate.send(KafkaTopics.ORDER_PLACED, savedOrder.getOrderId(), event).whenComplete((result, throwable) -> {
                if (throwable != null) {
                    log.error("Failed to publish OrderPlacedEvent for orderId {}: {}", savedOrder.getOrderId(), throwable.getMessage());
                } else {
                    log.info("OrderPlacedEvent published successfully for orderId: {}", savedOrder.getOrderId());
                }
            });
        } catch (Exception e) {
            log.error("Exception while sending event to Kafka: {}", e.getMessage(), e);
        }

        return savedOrder;
    }

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId).or(() -> {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        });
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found for user: " + userId);
        }
        return orders;
    }

    private void validateOrderRequest(OrderRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("OrderRequest cannot be null");
        }
        if (request.getUserId() == null || request.getUserId().isBlank()) {
            throw new IllegalArgumentException("UserId is required");
        }
        if (request.getStockSymbol() == null || request.getStockSymbol().isBlank()) {
            throw new IllegalArgumentException("StockSymbol is required");
        }
        if (request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (request.getPrice() == null || request.getPrice().doubleValue() <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (request.getOrderType() == null) {
            throw new IllegalArgumentException("OrderType is required");
        }
    }

    private OrderPlacedEvent buildOrderPlacedEvent(Order order) {
        return OrderPlacedEvent.builder().orderId(order.getOrderId()).userId(order.getUserId()).stockSymbol(order.getStockSymbol()).quantity(order.getQuantity()).price(order.getPrice()).orderType(order.getOrderType().name()).timestamp(order.getTimestamp().toString()).build();
    }
}
