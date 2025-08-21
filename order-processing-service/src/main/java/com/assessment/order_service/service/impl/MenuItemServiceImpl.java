package com.assessment.order_service.service.impl;

import com.assessment.order_service.dto.MenuItemDTO;
import com.assessment.order_service.exception.ResourceNotFoundException;
import com.assessment.order_service.mapper.MenuItemMapper;
import com.assessment.order_service.model.MenuItem;
import com.assessment.order_service.model.Shop;
import com.assessment.order_service.repository.MenuItemRepository;
import com.assessment.order_service.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    @Override
    public List<MenuItemDTO> getMenuItemsByShop(Long shopId) {
        Shop shop = new Shop();
        shop.setId(shopId);

        List<MenuItem> menuItems = menuItemRepository.findByShop(shop);
        return menuItems.stream().map(menuItemMapper::toDto).toList();

    }

    @Override
    public MenuItemDTO getMenuItemById(Long menuItemId) {
        return menuItemMapper.toDto(menuItemRepository.findById(menuItemId).orElseThrow(() -> new ResourceNotFoundException("Menu Item not found with ID : "+menuItemId)));
    }
}
