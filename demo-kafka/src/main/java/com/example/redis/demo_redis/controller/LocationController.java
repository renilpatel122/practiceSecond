//package com.example.redis.demo_redis.controller;
//
//import com.example.redis.demo_redis.listener.LocationConsumer;
//import com.example.redis.demo_redis.listener.LocationProducer;
//import com.example.redis.demo_redis.model.LocationDTO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/location")
//@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST})
//public class LocationController {
//
//    private final LocationProducer locationProducer;
//
//    // POST: Send location to Kafka
//    @PostMapping
//    public String updateLocation(@RequestBody LocationDTO location) {
//        try {
//            locationProducer.sendLocation(location);
//            return "Location sent to Kafka successfully";
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "Failed to send location";
//        }
//    }
//
//    // GET: Retrieve the latest location from consumer memory
//    @GetMapping
//    public LocationDTO getLatestLocation() {
//        return LocationConsumer.latestLocation;
//    }
//}