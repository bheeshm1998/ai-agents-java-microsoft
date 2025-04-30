package com.bheeshm.azure_ai_java.agent;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;

import java.util.List;

public class SummarizerAgent {

    private final ChatLanguageModel model;

    public SummarizerAgent(ChatLanguageModel model) {
        this.model = model;
    }

    public String process(String text) {
        List<ChatMessage> messages = List.of(
                new SystemMessage("You are an expert at distilling scene descriptions into effective image prompts for DALL-E. Summarize the following scene, focusing only on visual details essential for generating an accurate image. Exclude unnecessary context, keeping only the elements that add value to the visual prompt. Limit your summary to approximately 3000 characters."),
                new UserMessage("Scene description: \n" + text)
        );
        return model.chat(messages).aiMessage().text();
    }
}
