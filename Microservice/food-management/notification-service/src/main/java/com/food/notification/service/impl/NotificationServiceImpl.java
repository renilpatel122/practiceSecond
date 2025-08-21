package com.food.notification.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.notification.dto.NotificationRequest;
import com.food.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Sending Notification to {} via {}", request.getRecipient(), request.getType());

        try {
            String value = objectMapper.writeValueAsString(request);
            redisTemplate.opsForValue().set("notif:" + request.getRecipient(), value, Duration.ofMinutes(10));
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize notification request", e);
        }
    }
}
