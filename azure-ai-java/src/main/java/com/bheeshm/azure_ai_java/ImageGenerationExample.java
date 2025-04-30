package com.bheeshm.azure_ai_java;

import com.bheeshm.azure_ai_java.utils.DalleImageGenerator;
import org.springframework.beans.factory.annotation.Value;

public class ImageGenerationExample {

    @Value("${openai.api.key}")
    private String openaiApiKey;

//    @Value("${azure.openai.chat.api.key}")
//    private String azureOpenaiChatApiKey;
//
//    @Value("${azure.openai.dalle.api.key}")
//    private String azureOpenaiDalleApiKey;

//    public static void main(String[] args) {
//        String azureDalleEndpoint = "https://aaii-m9yhw4pi-swedencentral.openai.azure.com/openai/deployments/dall-e-3/images/generations?api-version=2024-02-01";
//        String azureApiKey = azureOpenaiDalleApiKey;
//
//        DalleImageGenerator dalleAzure = new DalleImageGenerator(azureDalleEndpoint, azureApiKey);
//
//        String openAiDalleEndpoint = "https://aaii-m9yhw4pi-swedencentral.openai.azure.com/openai/deployments/dall-e-3/images/generations?api-version=2024-02-01";
//        String openAiApiKey = openaiApiKey ;
//
//        DalleImageGenerator dalleOpenAi = new DalleImageGenerator(openAiApiKey, openAiDalleEndpoint);
//
//        String prompt = "A futuristic city with flying cars at sunset";
//        String prompt2 = "An orange cat. Art style: water color painting style";
//        String prompt3 = "An orange cat. Art style: pencil sketch";
//
////        String imageUrl = dalleAzure.generateImage(prompt2);
//
//        String imageUrl = dalleOpenAi.generateImage(prompt3);
//
//        System.out.println("Generated Image URL: " + imageUrl);
//
//    }
}
