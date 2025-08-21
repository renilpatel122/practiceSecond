package com.food.notification.kafka;

import com.food.notification.dto.NotificationRequest;
import com.food.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consume(NotificationRequest request) {
        log.info("Received Notification Request: {}", request);
        notificationService.sendNotification(request);
    }
}
