package com.assessment.order_service.service;

import com.assessment.order_service.dto.MenuItemDTO;

import java.util.List;

public interface MenuItemService {
    List<MenuItemDTO> getMenuItemsByShop(Long shopId);
    MenuItemDTO getMenuItemById(Long menuItemId);
}
