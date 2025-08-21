package com.example.redis.demo_redis.controller;


import com.example.redis.demo_redis.service.VideoProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
public class StreamingController {

    @Autowired
    private VideoProducerService producerService;

    @GetMapping("/start")
    public String startStreaming(@RequestParam String path) throws Exception {
        producerService.streamVideo(path);
        return "Streaming started!";
    }
}
