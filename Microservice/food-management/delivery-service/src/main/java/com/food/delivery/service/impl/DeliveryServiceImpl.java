package com.food.delivery.service.impl;

import com.food.delivery.dto.DeliveryRequestDTO;
import com.food.delivery.dto.DeliveryStatusUpdateDTO;
import com.food.delivery.entity.Delivery;
import com.food.delivery.kafka.DeliveryEventPublisher;
import com.food.delivery.repository.DeliveryRepository;
import com.food.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository repository;
    private final DeliveryEventPublisher eventPublisher;

    @Override
    public Delivery assignDelivery(DeliveryRequestDTO request) {
        Delivery delivery = Delivery.builder()
                .orderId(request.getOrderId())
                .deliveryPersonId(request.getDeliveryPersonId())
                .status("ASSIGNED")
                .assignedTime(LocalDateTime.now())
                .build();

        Delivery saved = repository.save(delivery);

        // Notify via Kafka
        eventPublisher.publishDeliveryAssigned(saved.getId(), saved.getDeliveryPersonId());

        return saved;
    }
    @Override
    @CachePut(value = "deliveryCache", key = "#update.deliveryId")
    public Delivery updateStatus(DeliveryStatusUpdateDTO update) {
        Delivery delivery = repository.findById(update.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(update.getNewStatus());
        if (update.getNewStatus().equals("DELIVERED")) {
            delivery.setDeliveredTime(LocalDateTime.now());
        }
        return repository.save(delivery);
    }

    @Override
    @Cacheable(value = "deliveryCache", key = "#deliveryId")
    public Delivery getDeliveryDetails(Long deliveryId) {
        return repository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
    }
}
