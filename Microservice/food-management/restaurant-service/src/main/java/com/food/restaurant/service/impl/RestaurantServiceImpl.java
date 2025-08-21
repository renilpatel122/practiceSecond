package com.food.restaurant.service.impl;

import com.food.restaurant.dto.MenuItemDTO;
import com.food.restaurant.dto.RestaurantDTO;
import com.food.restaurant.entity.MenuItem;
import com.food.restaurant.entity.Restaurant;
import com.food.restaurant.repository.MenuItemRepository;
import com.food.restaurant.repository.RestaurantRepository;
import com.food.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public RestaurantDTO addRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .cuisine(dto.getCuisine())
                .active(dto.getActive())
                .build();
        Restaurant saved = restaurantRepository.save(restaurant);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(r -> RestaurantDTO.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .location(r.getLocation())
                        .cuisine(r.getCuisine())
                        .active(r.getActive())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemDTO addMenuItem(MenuItemDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuItem item = MenuItem.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .available(dto.isAvailable())
                .restaurant(restaurant)
                .build();

        MenuItem saved = menuItemRepository.save(item);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId).stream()
                .map(item -> MenuItemDTO.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .description(item.getDescription())
                        .price(item.getPrice())
                        .available(item.isAvailable())
                        .restaurantId(restaurantId)
                        .build())
                .collect(Collectors.toList());
    }
}
