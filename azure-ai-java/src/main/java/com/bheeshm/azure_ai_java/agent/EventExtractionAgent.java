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
                        "Your task is to carefully read a given story text and extract important events/scenes as per instructions.\n" +
                        "You must only select characters from the provided list.\n" +
                        "Return the output strictly as a JSON array. Each element must be an object with three keys:\n" +
                        "\n" +
                        "\"sceneName\": A short, descriptive name for the event/scene (string).\n" +
                        "\n" +
                        "\"sceneDescription\": The scene description in detail including the settings, props, the emotion and the atmosphere there as one would describe to a sketch artist or a painter. It should also contain the characters' appearance and pose in that particular scene. Note that the Characters' name should be there in the character list provide (string).\n" +
                        "\n" +
                        "\"sceneSetting\": The scene setting, like backdrop in which the event is happening \n" +
                        "\n" +
                        "\"charactersInvolved\": An array of names (strings) of characters involved in that event, selected only from the provided character list.\n" +
                        "\n" +
                        "Maintain the exact number of events requested.\n" +
                        "No extra commentary. No explanation. Return only the JSON object, with no extra explanation or text or code block."),
                new UserMessage("Extract " + numberOfEvents + " most important scenes/events from the story. The character list is: " + charactersListAsString +  ". Story : \n" + story)
        );
        String response = model.chat(messages).aiMessage().text();
        return response;
    }
}