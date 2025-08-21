package com.assessment.order_service.controller;

import com.assessment.order_service.dto.ShopQueueDTO;
import com.assessment.order_service.service.ShopQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queues")
@RequiredArgsConstructor
public class ShopQueueController {

    private final ShopQueueService shopQueueService;

    @GetMapping("/shop/{shopId}")
    public List<ShopQueueDTO> getQueueByShop(@PathVariable Long shopId) {
        return shopQueueService.getQueueByShop(shopId);
    }

    @GetMapping("/customer/{customerId}")
    public List<ShopQueueDTO> getQueueByCustomer(@PathVariable Long customerId) {
        return shopQueueService.getQueueByCustomer(customerId);
    }
}
