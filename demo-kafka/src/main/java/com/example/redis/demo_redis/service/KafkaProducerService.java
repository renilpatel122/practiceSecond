//package com.example.redis.demo_redis.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class KafkaProducerService {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private static final String TOPIC = "user-events";
//
//    public void sendMessage(String message) {
//        kafkaTemplate.send(TOPIC, message);
//        System.out.println("Sent message: " + message);
//    }
//}
