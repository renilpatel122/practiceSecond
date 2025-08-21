package com.food.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String recipient; // email, phone, etc.
    private String message;
    private String type; // email, sms, push
}
