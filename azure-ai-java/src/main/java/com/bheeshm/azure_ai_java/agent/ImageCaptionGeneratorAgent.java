package com.bheeshm.azure_ai_java.agent;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

import java.util.List;

public class ImageCaptionGeneratorAgent {

    private final ChatLanguageModel model;

    public ImageCaptionGeneratorAgent(ChatLanguageModel model) {
        this.model = model;
    }

    public String process(String sceneDescription) {
        List<ChatMessage> messages = List.of(
                new SystemMessage("You are a helpful assistant that generates image captions. The image is described using text"),
                new UserMessage("Generate a caption for the following image description:\n" + sceneDescription)
        );
        String response = model.chat(messages).aiMessage().text();
        return response;
    }
}
