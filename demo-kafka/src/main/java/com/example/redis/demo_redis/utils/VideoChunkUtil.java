package com.example.redis.demo_redis.utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class VideoChunkUtil {
    public static List<byte[]> readVideoInChunks(String filePath, int chunkSize) throws IOException {
        System.out.println("📁 Reading video file from path: " + filePath);

        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("❌ File not found at: " + filePath);
        }

        byte[] data = Files.readAllBytes(file.toPath());
        System.out.println("✅ File read successfully. Total size: " + data.length + " bytes");

        List<byte[]> chunks = new ArrayList<>();

        int totalChunks = 0;

        for (int i = 0; i < data.length; i += chunkSize) {
            int end = Math.min(data.length, i + chunkSize);
            chunks.add(Arrays.copyOfRange(data, i, end));
            totalChunks++;
        }
        System.out.println("📦 Total chunks created: " + totalChunks + ", each of size up to: " + chunkSize + " bytes");

        return chunks;
    }
}

