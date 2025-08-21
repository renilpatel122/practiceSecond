package com.food.order.controller;

import com.food.order.dto.OrderDTO;
import com.food.order.service.OrderService;
import com.food.order.service.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final TokenValidator tokenValidator;

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(
            @RequestBody OrderDTO dto,
            @RequestHeader("Authorization") String token) {

        if (!tokenValidator.isCustomer(token)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(orderService.placeOrder(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
