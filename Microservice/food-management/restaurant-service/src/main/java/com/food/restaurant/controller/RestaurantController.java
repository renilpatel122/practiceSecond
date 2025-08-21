package com.food.restaurant.controller;

import com.food.restaurant.dto.MenuItemDTO;
import com.food.restaurant.dto.RestaurantDTO;
import com.food.restaurant.service.RestaurantService;
import com.food.restaurant.service.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final TokenValidator tokenValidator;

    @PostMapping
    public ResponseEntity<RestaurantDTO> addRestaurant(
            @RequestBody RestaurantDTO dto,
            @RequestHeader("Authorization") String token) {

        if (!tokenValidator.isAdmin(token)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(restaurantService.addRestaurant(dto));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<MenuItemDTO> addMenuItem(
            @PathVariable Long restaurantId,
            @RequestBody MenuItemDTO dto,
            @RequestHeader("Authorization") String token) {

        if (!tokenValidator.isAdmin(token)) {
            return ResponseEntity.status(403).build();
        }

        dto.setRestaurantId(restaurantId);
        return ResponseEntity.ok(restaurantService.addMenuItem(dto));
    }

    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<List<MenuItemDTO>> getMenu(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getMenuItemsByRestaurantId(restaurantId));
    }
}
