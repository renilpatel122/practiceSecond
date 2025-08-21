package com.food.delivery.service;

import com.food.delivery.dto.DeliveryRequestDTO;
import com.food.delivery.dto.DeliveryStatusUpdateDTO;
import com.food.delivery.entity.Delivery;

public interface DeliveryService {
    Delivery assignDelivery(DeliveryRequestDTO request);
    Delivery updateStatus(DeliveryStatusUpdateDTO update);
    Delivery getDeliveryDetails(Long deliveryId);
}
