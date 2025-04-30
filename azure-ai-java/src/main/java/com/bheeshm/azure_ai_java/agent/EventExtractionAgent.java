package com.bheeshm.azure_ai_java.agent;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

import java.util.Arrays;
import java.util.List;

public class EventExtractionAgent {

    private final ChatLanguageModel model;

    public EventExtractionAgent(ChatLanguageModel model) {
        this.model = model;
    }

    public String process(String story, int numberOfEvents, List<String> charactersList) {
        String charactersListAsString = String.join(", ", charactersList);
        List<ChatMessage> messages = List.of(
                new SystemMessage("You are an information extraction assistant.\n" +
                        "\n" +
                        "Carefully read the provided story text and extract the specified number of key events or scenes, following these instructions:\n" +
                        "\n" +
                        "Only select characters from the supplied character list.\n" +
                        "For each event/scene, return an object with these keys:\n" +
                        "\"sceneName\": A short, descriptive name for the event or scene (string).\n" +
                        "\"sceneDescription\": A detailed, visually rich description of the scene, including setting, props, atmosphere, mood, characters’ appearances and poses, as you would describe to a sketch artist or painter. Only use character names from the provided list.\n" +
                        "\"sceneSetting\": The backdrop or setting of the scene (string).\n" +
                        "\"charactersInvolved\": An array of character names (strings), chosen only from the provided character list.\n" +
                        "Return only a JSON array of such objects, with no extra commentary, explanation, or code blocks. Maintain the exact number of events or scenes requested.\n" +
                        "\n"),
                new UserMessage("Extract the " +  numberOfEvents  + "most important scenes/events from the story provided below. For each, include only characters from the following list: " +  charactersListAsString + ". Story text: " + story));
        String response = model.chat(messages).aiMessage().text();
        return response;
    }
}