package com.assessment.order_service.service;

import com.assessment.order_service.dto.InventoryItemDTO;

public interface InventoryItemService {
    InventoryItemDTO getInventoryItemByMenuItem(Long menuItemId);
    void updateInventoryItem(InventoryItemDTO inventoryItemDTO);

}
