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
                new SystemMessage("You are a text extraction assistant specialized in parsing stories. Given a raw story text that may include a title, author name, preface, editorial notes, epilogue, and the main story content, your task is to extract and separate these elements into a JSON object with three fields:\r\n" + //
                                        "\r\n" + //
                                        "- \"title\": The title of the story as a string. If no clear title is present, return an empty string.\r\n" + //
                                        "- \"author\": The author’s name as a string. If no author is mentioned, return an empty string.\r\n" + //
                                        "- \"content\": The main story content as a string, with all non-story elements removed (such as preface, editorial notes, epilogue, copyright, dedications, etc.).\r\n" + //
                                        "\r\n" + //
                                        "Return only the JSON object, with no extra explanation or text or code block.\r\n" + //
                                        ""),
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