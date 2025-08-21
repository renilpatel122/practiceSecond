package com.assessment.order_service.mapper;

import com.assessment.order_service.dto.InventoryItemDTO;
import com.assessment.order_service.model.InventoryItem;
import com.assessment.order_service.model.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemMapper {

    public InventoryItemDTO toDto(InventoryItem item) {
        return InventoryItemDTO.builder()
                .id(item.getId())
                .menuItemId(item.getMenuItem().getId())
                .availableQuantity(item.getQuantity())
                .build();
    }

    public InventoryItem toEntity(InventoryItemDTO dto, MenuItem menuItem) {
        return InventoryItem.builder()
                .id(dto.getId())
                .menuItem(menuItem)
                .quantity(dto.getAvailableQuantity())
                .build();
    }
}
