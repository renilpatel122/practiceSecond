//package com.example.redis.demo_redis.listener;
//
//import com.example.redis.demo_redis.config.KafkaConfig;
//import com.example.redis.demo_redis.model.LocationDTO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//// LocationConsumer.java
//@Component
//@Slf4j
//public class LocationConsumer {
//    public static volatile LocationDTO latestLocation = new LocationDTO(0, 0);
//
//    @KafkaListener(topics = KafkaConfig.TOPIC, groupId = "location-group")
//    public void consume(String message) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        latestLocation = mapper.readValue(message, LocationDTO.class);
//        log.info("Received location update: {}", latestLocation);
//    }
//}
