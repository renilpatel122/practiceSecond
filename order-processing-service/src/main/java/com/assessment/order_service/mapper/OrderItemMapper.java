package com.assessment.order_service.mapper;

import com.assessment.order_service.dto.OrderItemDTO;
import com.assessment.order_service.model.MenuItem;
import com.assessment.order_service.model.Order;
import com.assessment.order_service.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public static OrderItemDTO toDto(OrderItem item) {
        return OrderItemDTO.builder()
                .id(item.getId())
                .menuItemId(item.getMenuItem().getId())
                .quantity(item.getQuantity())
                .build();
    }

    public static OrderItem toEntity(OrderItemDTO dto, Order order, MenuItem menuItem) {
        return OrderItem.builder()
                .id(dto.getId())
                .order(order)
                .menuItem(menuItem)
                .quantity(dto.getQuantity())
                .build();
    }
}
