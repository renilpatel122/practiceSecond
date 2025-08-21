package com.example.redis.demo_redis.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class KafkaStreamConsumerService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "video-stream", groupId = "my-group")
    public void consume(String chunk) {
        try {
//            String encodedChunk = Base64.getEncoder().encodeToString((chunk));
            messagingTemplate.convertAndSend("/topic/video", chunk);
        } catch (Exception e) {
            e.printStackTrace();  // Handle your error here
        }
    }

}
