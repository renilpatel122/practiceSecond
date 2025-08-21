package com.assessment.order_service.mapper;

import com.assessment.order_service.dto.MenuItemDTO;
import com.assessment.order_service.model.MenuItem;
import com.assessment.order_service.model.Shop;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper {

    public MenuItemDTO toDto(MenuItem menuItem) {
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .price(menuItem.getPrice())
                .shopId(menuItem.getShop().getId())
                .build();
    }

    public MenuItem toEntity(MenuItemDTO dto, Shop shop) {
        return MenuItem.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .shop(shop)
                .build();
    }
}
