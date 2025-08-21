package com.example.inventory.controller;

import com.example.api.controller.InventoryApi;
import com.example.api.model.Inventory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController implements InventoryApi {

    @Override
    public ResponseEntity<Inventory> getInventory(Long id) {
        Inventory inventory = new Inventory()
                .productId(id)
                .quantity(100)
                .warehouseLocation("Warehouse A");

        return ResponseEntity.ok(inventory);
    }
}