package com.assessment.order_service.controller;

import com.assessment.order_service.dto.MenuItemDTO;
import com.assessment.order_service.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping("/shop/{shopId}")
    public List<MenuItemDTO> getMenuItemsByShop(@PathVariable Long shopId) {
        return menuItemService.getMenuItemsByShop(shopId);
    }
}
