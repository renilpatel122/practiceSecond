package com.food.delivery.controller;

import com.food.delivery.dto.DeliveryRequestDTO;
import com.food.delivery.dto.DeliveryStatusUpdateDTO;
import com.food.delivery.entity.Delivery;
import com.food.delivery.service.DeliveryService;
import com.food.delivery.service.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final TokenValidator tokenValidator;

    @PostMapping("/assign")
    public ResponseEntity<Delivery> assignDelivery(
            @RequestBody DeliveryRequestDTO request,
            @RequestHeader("Authorization") String token) {

        if (!tokenValidator.isAdmin(token)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(deliveryService.assignDelivery(request));
    }

    @PutMapping("/status")
    public ResponseEntity<Delivery> updateStatus(@RequestBody DeliveryStatusUpdateDTO update) {
        return ResponseEntity.ok(deliveryService.updateStatus(update));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.getDeliveryDetails(id));
    }
}
