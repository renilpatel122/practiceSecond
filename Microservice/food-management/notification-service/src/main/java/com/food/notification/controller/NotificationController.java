package com.food.notification.controller;

import com.food.notification.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final KafkaTemplate<String, NotificationRequest> kafkaTemplate;

    @PostMapping
    public String sendToKafka(@RequestBody NotificationRequest request) {
        kafkaTemplate.send("notification-topic", request);
        return "Notification request sent to Kafka.";
    }
}
