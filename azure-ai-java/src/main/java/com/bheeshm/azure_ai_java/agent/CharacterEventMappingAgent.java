package com.bheeshm.azure_ai_java.agent;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

import java.util.List;

public class CharacterEventMappingAgent {

    private final ChatLanguageModel model;

    public CharacterEventMappingAgent(ChatLanguageModel model) {
        this.model = model;
    }

    public List<String> process(String story, List<String> events) {
        List<ChatMessage> messages = List.of(
                new SystemMessage("You are a helpful assistant that maps characters to events in a story."),
                new UserMessage("For each of the following events, list the characters involved:\n" + String.join("\n", events) + "\nStory:\n" + story)
        );
        String response = model.chat(messages).aiMessage().text();
        return List.of(response.split("\n"));
    }
}