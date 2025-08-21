//package com.example.redis.demo_redis.controller;
//
//import com.example.redis.demo_redis.service.KafkaProducerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/kafka")
//@RequiredArgsConstructor
//public class KafkaController {
//
//    private final KafkaProducerService producer;
//
//    @PostMapping("/publish")
//    public String publishMessage(@RequestParam String message) {
//        producer.sendMessage(message);
//        return "Message sent to Kafka: " + message;
//    }
//}
