package com.assessment.order_service.mapper;

import com.assessment.order_service.dto.InventoryItemDTO;
import com.assessment.order_service.dto.OrderDTO;
import com.assessment.order_service.dto.OrderItemDTO;
import com.assessment.order_service.exception.ResourceNotFoundException;
import com.assessment.order_service.model.*;
import com.assessment.order_service.repository.CustomerRepository;
import com.assessment.order_service.repository.MenuItemRepository;
import com.assessment.order_service.repository.ShopRepository;
import com.assessment.order_service.service.InventoryItemService;
import com.assessment.order_service.service.MenuItemService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final MenuItemRepository menuItemRepository;
    private final CustomerRepository customerRepository;
    private final ShopRepository shopRepository;
    private final InventoryItemService inventoryItemService;

    public OrderDTO toDto(Order order) {
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(OrderItemMapper::toDto)
                .collect(Collectors.toList());

        return OrderDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .shopId(order.getShop().getId())
                .queueNumber(order.getQueueNumber())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .orderItems(itemDTOs)
                .build();
    }


}
