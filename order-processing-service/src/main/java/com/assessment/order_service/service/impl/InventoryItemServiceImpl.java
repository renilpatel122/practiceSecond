package com.assessment.order_service.service.impl;

import com.assessment.order_service.dto.InventoryItemDTO;
import com.assessment.order_service.exception.ResourceNotFoundException;
import com.assessment.order_service.mapper.InventoryItemMapper;
import com.assessment.order_service.model.InventoryItem;
import com.assessment.order_service.model.MenuItem;
import com.assessment.order_service.repository.InventoryItemRepository;
import com.assessment.order_service.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    public InventoryItemDTO getInventoryItemByMenuItem(Long menuItemId) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemId);
        return inventoryItemRepository.findByMenuItem(menuItem)
                .map(inventoryItemMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Inventory Item not found"));
    }

    @Override
    public void updateInventoryItem(InventoryItemDTO dto) {
        InventoryItem item = inventoryItemRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Inventory Item not found with item ID : "+dto.getId()));
        inventoryItemRepository.save(item);
    }
}
