package com.bheeshm.azure_ai_java.agent;

import com.bheeshm.azure_ai_java.utils.DalleImageGenerator;

public class ImageGenerationAgent {

    private final DalleImageGenerator dalleImageGenerator;

    public ImageGenerationAgent(String endpoint, String apiKey) {
        this.dalleImageGenerator = new DalleImageGenerator(apiKey, endpoint);
    }

    public String generateImage(String eventDescription) {
        return dalleImageGenerator.generateImage(eventDescription);
    }
}