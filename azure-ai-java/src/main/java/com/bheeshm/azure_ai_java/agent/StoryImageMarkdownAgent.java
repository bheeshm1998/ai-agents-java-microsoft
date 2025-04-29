package com.bheeshm.azure_ai_java.agent;

import com.bheeshm.azure_ai_java.model.Image;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;

import java.util.List;
import java.util.stream.Collectors;

public class StoryImageMarkdownAgent {

    private final ChatLanguageModel model;

    public StoryImageMarkdownAgent(ChatLanguageModel model) {
        this.model = model;
    }

    public String process(String storyText, List<Image> images) {

        String imagesDescription = images.stream()
                .map(image -> "- Caption: \"" + image.getCaption() + "\", URL: " + image.getUrl())
                .collect(Collectors.joining("\n"));

        List<ChatMessage> messages = List.of(
                new SystemMessage("new SystemMessage(\"You are an expert Markdown editor.\\n\" +\n" +
                        "    \"You will be provided with a story text and a list of images with captions and URLs.\\n\\n\" +\n" +
                        "    \"Your task:\\n\" +\n" +
                        "    \"- Make some texts bold, italic as you feel appropriate according to the story.\\n\" +\n" +
                        "    \"- Insert images AFTER the paragraph that matches the caption.\\n\" +\n" +
                        "    \"- Format the story beautifully using Markdown:\\n\" +\n" +
                        "    \"  - Use `#` for title\\n\" +\n" +
                        "    \"  - Use `*` for italic text\\n\" +\n" +
                        "    \"  - Use `**` for bold text\\n\" +\n" +
                        "    \"  - Insert images using Markdown syntax like `![caption](url)`\\n\" +\n" +
                        "    \"- Maintain proper paragraph spacing (double line breaks).\\n\" +\n" +
                        "    \"- DO NOT wrap the output inside code blocks like ``` or ```markdown.\\n\" +\n" +
                        "    \"- DO NOT add any extra text like 'Here is your Markdown' or anything else.\\n\" +\n" +
                        "    \"- Just return the clean formatted text directly.\\n\\n\" +\n" +
                        "    \"Be neat and careful.\"\n" +
                        ")\n"),
                new UserMessage("" +
                        "Image details: " + imagesDescription +
                        "\n\n Story: " + storyText
                       )
        );
        return model.chat(messages).aiMessage().text();
    }
}

