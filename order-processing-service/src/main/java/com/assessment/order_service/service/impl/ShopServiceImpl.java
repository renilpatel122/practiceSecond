package com.assessment.order_service.service.impl;

import com.assessment.order_service.dto.ShopDTO;
import com.assessment.order_service.mapper.ShopMapper;
import com.assessment.order_service.model.Shop;
import com.assessment.order_service.repository.ShopRepository;
import com.assessment.order_service.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    @Override
    public ShopDTO getShopById(Long id) {
        Optional<Shop> shop = shopRepository.findById(id);
        return shop.map(shopMapper::toDto).orElseThrow(() -> new RuntimeException("Shop not found"));
    }

    @Override
    public List<ShopDTO> getAllShops() {
        return shopRepository.findAll().stream().map(shopMapper::toDto).toList();
    }

    @Override
    public ShopDTO createShop(ShopDTO shopDTO) {
        Shop shop = shopMapper.toEntity(shopDTO);
        Shop savedShop = shopRepository.save(shop);
        return shopMapper.toDto(savedShop);
    }

    @Override
    public ShopDTO updateShop(Long id, ShopDTO shopDTO) {
        Shop existingShop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        shopMapper.updateEntityFromDto(shopDTO, existingShop);
        Shop updatedShop = shopRepository.save(existingShop);
        return shopMapper.toDto(updatedShop);
    }

    @Override
    public void deleteShop(Long id) {
        Shop existingShop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        shopRepository.delete(existingShop);
    }
}
