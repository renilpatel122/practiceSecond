package com.example.redis.demo_redis.service;

import com.example.redis.demo_redis.utils.VideoChunkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Base64;
import java.util.List;

@Service
public class VideoProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "video-stream";

    public void streamVideo(String filePath) throws Exception {
        File videoFile = new File("/videos/" + filePath);
        List<byte[]> chunks = VideoChunkUtil.readVideoInChunks(videoFile.getAbsolutePath(), 4096);

        for (byte[] chunk : chunks) {
            kafkaTemplate.send(TOPIC, Base64.getEncoder().encodeToString(chunk));
            Thread.sleep(50); // simulate real-time
        }
    }
}
