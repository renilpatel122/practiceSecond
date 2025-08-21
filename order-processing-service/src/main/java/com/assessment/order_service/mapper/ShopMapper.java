package com.assessment.order_service.mapper;

import com.assessment.order_service.dto.ShopDTO;
import com.assessment.order_service.model.Shop;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper {

    public ShopDTO toDto(Shop shop) {
        return ShopDTO.builder()
                .id(shop.getId())
                .name(shop.getName())
                .location(shop.getLocation())
                .contactNumber(shop.getContactNumber())
                .openTime(shop.getOpenTime())
                .closeTime(shop.getCloseTime())
                .numberOfQueues(shop.getNumberOfQueues())
                .queueMaxSize(shop.getQueueMaxSize())
                .build();
    }

    public Shop toEntity(ShopDTO dto) {
        return Shop.builder()
                .id(dto.getId())
                .name(dto.getName())
                .location(dto.getLocation())
                .contactNumber(dto.getContactNumber())
                .openTime(dto.getOpenTime())
                .closeTime(dto.getCloseTime())
                .numberOfQueues(dto.getNumberOfQueues())
                .queueMaxSize(dto.getQueueMaxSize())
                .build();
    }
    public void updateEntityFromDto(ShopDTO dto, Shop existingShop) {
        // Update fields that can be updated
        existingShop.setName(dto.getName());
        existingShop.setLocation(dto.getLocation());
        existingShop.setContactNumber(dto.getContactNumber());
        existingShop.setOpenTime(dto.getOpenTime());
        existingShop.setCloseTime(dto.getCloseTime());
        existingShop.setNumberOfQueues(dto.getNumberOfQueues());
        existingShop.setQueueMaxSize(dto.getQueueMaxSize());
    }

}
