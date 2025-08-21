package com.food.notification.service;

import com.food.notification.dto.NotificationRequest;

public interface NotificationService {
    void sendNotification(NotificationRequest request);
}
