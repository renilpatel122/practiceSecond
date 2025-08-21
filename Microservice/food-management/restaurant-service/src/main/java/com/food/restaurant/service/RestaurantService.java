package com.food.restaurant.service;

import com.food.restaurant.dto.MenuItemDTO;
import com.food.restaurant.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    RestaurantDTO addRestaurant(RestaurantDTO dto);
    List<RestaurantDTO> getAllRestaurants();

    MenuItemDTO addMenuItem(MenuItemDTO dto);
    List<MenuItemDTO> getMenuItemsByRestaurantId(Long restaurantId);
}
