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
                new SystemMessage("You are an expert summarizer of text. Summarize the below description of a scene in such a way that it can be drawn by an artist perfectly. Only include those details in the text which would add value ni the context of an image drawn. Restrict the output to about 900 characters."),
                new UserMessage("Scene description: \n" + text)
        );
        return model.chat(messages).aiMessage().text();
    }
}
