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
                new SystemMessage("You are a helpful assistant skilled at generating image captions. You will be given a textual description of an image; write a clear and concise caption that accurately reflects the content."),
                new UserMessage("Using the description below, generate a caption that best captures the main subject and action of the image:\n" + sceneDescription)
        );
        String response = model.chat(messages).aiMessage().text();
        return response;
    }
}
