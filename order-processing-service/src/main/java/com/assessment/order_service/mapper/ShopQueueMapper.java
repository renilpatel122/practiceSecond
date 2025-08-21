package com.assessment.order_service.mapper;

import com.assessment.order_service.dto.ShopQueueDTO;
import com.assessment.order_service.model.Customer;
import com.assessment.order_service.model.Shop;
import com.assessment.order_service.model.ShopQueue;
import org.springframework.stereotype.Component;

@Component
public class ShopQueueMapper {

    public ShopQueueDTO toDto(ShopQueue queue) {
        return ShopQueueDTO.builder()
                .id(queue.getId())
                .shopId(queue.getShop().getId())
                .customerId(queue.getCustomer().getId())
                .queueNumber(queue.getQueueNumber())
                .status(queue.getStatus())
                .build();
    }

    public static ShopQueue toEntity(ShopQueueDTO dto, Shop shop, Customer customer) {
        return ShopQueue.builder()
                .id(dto.getId())
                .shop(shop)
                .customer(customer)
                .queueNumber(dto.getQueueNumber())
                .status(dto.getStatus())
                .build();
    }
}
