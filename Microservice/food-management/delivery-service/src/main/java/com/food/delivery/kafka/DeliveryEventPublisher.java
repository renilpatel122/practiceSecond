package com.food.delivery.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishDeliveryAssigned(Long deliveryId, Long deliveryPersonId) {
        String message = "Delivery assigned: ID=" + deliveryId + ", Person=" + deliveryPersonId;
        kafkaTemplate.send("delivery-assigned-topic", message);
    }
}
