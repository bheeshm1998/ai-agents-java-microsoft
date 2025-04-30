package com.bheeshm.azure_ai_java.utils;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;

@Configuration
public class AzureOpenAiConfig {

    static Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing() // avoids crashing in CI
            .load();;

        @Value("${openai.api.key}")
        private String openaiApiKey;
//    private String openaiApiKey = dotenv.get("OPENAI_API_KEY", System.getenv("OPENAI_API_KEY"));


//        @Value("${azure.openai.chat.api.key}")
    private String azureOpenaiChatApiKey;
//    private String azureOepnaiChatApiKey = dotenv.get("AZURE_OPENAI_CHAT_API_KEY");


//        @Value("${azure.openai.dalle.api.key}")
    private String azureOpenaiDalleApiKey;
//    private String azureOpenaiDalleApiKey = dotenv.get("AZURE_OPENAI_DALLE_API_KEY");


    @Bean
    public ChatLanguageModel chatLanguageModel() {
        if (openaiApiKey == null || openaiApiKey.isEmpty() || openaiApiKey.equals("your_openai_key_here")) {
            throw new IllegalStateException("OpenAI API key is not properly configured");
        }

        // For debugging (don't log full key in production)
//        System.out.println("API key detected (first 5 chars): " + openaiApiKey.substring(0, Math.min(5, openaiApiKey.length())));

        return OpenAiChatModel.builder()
                .apiKey(openaiApiKey)
                .modelName("gpt-4.1")
                .temperature(0.5)
                .timeout(Duration.ofSeconds(120))
                .build();
    }

    @Bean
    public DalleImageGenerator imageGenerator(){
        String openAiDalleEndpoint = "https://api.openai.com/v1/images/generations";
        String openAiApiKey = this.openaiApiKey;

        DalleImageGenerator dalle = new DalleImageGenerator(openAiApiKey, openAiDalleEndpoint);
        return dalle;
    }

//        @Bean
//    public ChatLanguageModel chatLanguageModel() {
//        return AzureOpenAiChatModel.builder()
//                .apiKey(this.azureOepnaiChatApiKey)
//                .endpoint("https://ai-aaiitbbs4609ai477650686573.openai.azure.com/")
//                .deploymentName("gpt-4o-mini")
//                .temperature(0.1)
//                .maxTokens(8192)
//                .timeout(Duration.ofSeconds(100))
//                .build();
//    }

//    @Bean
//    public DalleImageGenerator imageGenerator(){
//        String azureDalleEndpoint = "https://aaii-m9yhw4pi-swedencentral.openai.azure.com/openai/deployments/dall-e-3/images/generations?api-version=2024-02-01";
//        String azureApiKey = this.azureOepnaiDalleApiKey;
//
//        DalleImageGenerator dalle = new DalleImageGenerator(azureDalleEndpoint, azureApiKey);
//        return dalle;
//    }


}
