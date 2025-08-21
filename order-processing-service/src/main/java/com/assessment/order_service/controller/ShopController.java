package com.assessment.order_service.controller;

import com.assessment.order_service.dto.ShopDTO;
import com.assessment.order_service.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/{id}")
    public ShopDTO getShopById(@PathVariable Long id) {
        return shopService.getShopById(id);
    }

    @GetMapping
    public List<ShopDTO> getAllShops() {
        return shopService.getAllShops();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO createShop(@RequestBody ShopDTO shopDTO) {
        return shopService.createShop(shopDTO);
    }

    @PutMapping("/{id}")
    public ShopDTO updateShop(@PathVariable Long id, @RequestBody ShopDTO shopDTO) {
        return shopService.updateShop(id, shopDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
    }
}
