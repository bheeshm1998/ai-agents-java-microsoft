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

    public String process(String storyTitle, String author,  String storyContent, List<Image> images) {

        String imagesDescription = images.stream()
                .map(image -> "- Caption: \"" + image.getCaption() + "\", URL: " + image.getUrl())
                .collect(Collectors.joining("\n"));

        List<ChatMessage> messages = List.of(
                new SystemMessage("You are an expert Markdown editor.\n" +
                        "\n" +
                        "You will receive a story title, author, content, and a list of images (with captions and URLs).\n" +
                        "\n" +
                        "Your task:\n" +
                        "\n" +
                        "Format the story in Markdown: title, author, and content must be clearly and correctly formatted. Do not alter the story content.\n" +
                        "Add bold or italic emphasis to text where appropriate, based on the story’s context.\n" +
                        "Insert images at relevant points in the story, using their captions and context to determine placement. Use the Markdown image format: ![caption](url)\n" +
                        "Ensure proper paragraph spacing (two line breaks between paragraphs).\n" +
                        "Do not wrap the output in any code blocks (such as ormarkdown).\n" +
                        "Do not add extra commentary or introductory text—output only the final, clean, formatted Markdown.\n" +
                        "Be precise, neat, and maintain fidelity to the original story."),
                new UserMessage("" +
                        "Image details: " + imagesDescription +
                        "\n\n Story title: " + storyTitle +
                        "\n\n Author: " + author +
                        "\n\n Content: " + storyContent
                       )
        );
        return model.chat(messages).aiMessage().text();
    }
}

