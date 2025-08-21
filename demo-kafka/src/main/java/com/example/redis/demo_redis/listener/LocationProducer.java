//package com.example.redis.demo_redis.listener;
//
//import com.example.redis.demo_redis.config.KafkaConfig;
//import com.example.redis.demo_redis.model.LocationDTO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//// LocationProducer.java
//@Component
//@RequiredArgsConstructor
//public class LocationProducer {
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public void sendLocation(LocationDTO location) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        kafkaTemplate.send(KafkaConfig.TOPIC, mapper.writeValueAsString(location));
//    }
//}
//
