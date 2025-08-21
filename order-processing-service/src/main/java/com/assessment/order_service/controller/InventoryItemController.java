package com.assessment.order_service.controller;

import com.assessment.order_service.dto.InventoryItemDTO;
import com.assessment.order_service.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory-items")
@RequiredArgsConstructor
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    @GetMapping("/menu-item/{menuItemId}")
    public InventoryItemDTO getInventoryItemByMenuItem(@PathVariable Long menuItemId) {
        return inventoryItemService.getInventoryItemByMenuItem(menuItemId);
    }
}
