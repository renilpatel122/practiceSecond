package com.assessment.order_service.service;

import com.assessment.order_service.dto.ShopDTO;

import java.util.List;

public interface ShopService {
    ShopDTO getShopById(Long id);
    List<ShopDTO> getAllShops();
    ShopDTO createShop(ShopDTO shopDTO);
    ShopDTO updateShop(Long id, ShopDTO shopDTO);
    void deleteShop(Long id);
}
