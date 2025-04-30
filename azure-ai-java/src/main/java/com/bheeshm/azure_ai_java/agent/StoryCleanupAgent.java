package com.bheeshm.azure_ai_java.agent;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.azure.AzureOpenAiStreamingChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;


import java.util.List;

public class StoryCleanupAgent {

    private final
    ChatLanguageModel model;

    public StoryCleanupAgent(
            ChatLanguageModel model) {
        this.model = model;
    }

    public String cleanStory(String story) {
        List<ChatMessage> messages = List.of(
                new SystemMessage("You are a text extraction assistant specializing in parsing stories. Given a raw story text that may include a title, author name, preface, editorial notes, epilogue, and main story content, extract and separate these elements into a JSON object with the following fields:\n" +
                        "\n" +
                        "\"title\": The story title as a string. If no clear title is present, return an empty string.\n" +
                        "\"author\": The author’s name as a string. If not mentioned, return an empty string.\n" +
                        "\"content\": The main story content as a string, with all non-story elements (such as preface, editorial notes, epilogue, copyright, dedications, etc.) removed.\n" +
                        "Output only the JSON object. Do not include any explanations, extra text, or code blocks."),
                new UserMessage("Extract the title, author, and main content from the following story text. Return the result as a JSON object with keys \"title\", \"author\", and \"content\".\r\n" + //
                                        "\r\n" + //
                                        "Story text:\r\n" + //
                                        "\"\"\"\r\n" + //
                                        story + "\r\n" + //
                                        "\"\"\"\r\n" + //
                                        "\r\n" )
        );
        return model.chat(messages).aiMessage().text();
    }
}