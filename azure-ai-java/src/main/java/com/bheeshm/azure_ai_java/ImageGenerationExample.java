package com.bheeshm.azure_ai_java;

import com.bheeshm.azure_ai_java.utils.DalleImageGenerator;
import org.springframework.beans.factory.annotation.Value;

public class ImageGenerationExample {

    @Value("${openai.api.key}")
    static private String openaiApiKey;

//    @Value("${azure.openai.chat.api.key}")
//    private String azureOpenaiChatApiKey;
//
//    @Value("${azure.openai.dalle.api.key}")
//    private String azureOpenaiDalleApiKey;

    public static void main(String[] args) {
        String azureDalleEndpoint = "https://aaii-m9yhw4pi-swedencentral.openai.azure.com/openai/deployments/dall-e-3/images/generations?api-version=2024-02-01";
//        String azureApiKey = azureOpenaiDalleApiKey;

//        DalleImageGenerator dalleAzure = new DalleImageGenerator(azureDalleEndpoint, azureApiKey);

        String openAiDalleEndpoint = "https://api.openai.com/v1/images/generations";
//        String openAiApiKey = openaiApiKey ;

        DalleImageGenerator dalleOpenAi = new DalleImageGenerator(openaiApiKey, openAiDalleEndpoint);

        String prompt = "A futuristic city with flying cars at sunset";
        String prompt2 = "An orange cat. Art style: water color painting style";
        String prompt3 = "Two women stand inside a small, sparsely furnished hair shop with a businesslike, utilitarian atmosphere. The setting is an upstairs room with plain walls and minimal decor, evoking a modest, practical workspace. \n" +
                "\n" +
                "Della, a young, slender Caucasian woman with fair skin, stands anxiously in the center. She wears an old brown jacket and matching hat, her cheeks flushed with emotion and cold. She is in the act of removing her hat, revealing extremely long, rippling, shiny brown hair that cascades below her knees and catches the dim light. Her expression is nervous and emotional, her posture slightly tense.\n" +
                "\n" +
                "Facing her is Madame Sofronie, a large, pale-skinned Caucasian woman with a chilly, impassive demeanor. She is businesslike and unemotional, her posture upright and professional. Madame Sofronie is lifting and examining Della’s long hair with a practiced, appraising hand, her face showing no warmth or personal interest.\n" +
                "\n" +
                "The overall mood is tense and transactional, with Della’s desperation and excitement subtly visible, contrasted by Madame Sofronie’s detached professionalism.\n" +
                "\n" +
                "Pixar-style: Characters are rendered with expressive, slightly exaggerated Pixar-like features—large, emotive eyes, smooth textures, and gentle, warm lighting that highlights the emotional contrast between the two women. The shop’s background is simple but detailed enough to convey its utilitarian function, with minimal furnishings such as a small table, a mirror, and a few hair goods displayed.";

//        String imageUrl = dalleAzure.generateImage(prompt2);

        String imageUrl = dalleOpenAi.generateImage(prompt3);

        System.out.println("Generated Image URL: " + imageUrl);

    }
}
