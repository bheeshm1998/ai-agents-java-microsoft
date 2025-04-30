package com.bheeshm.azure_ai_java.agent;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

import java.util.List;

public class CharacterExtractionAgent {

    private final ChatLanguageModel model;

    public CharacterExtractionAgent(ChatLanguageModel model) {
        this.model = model;
    }

    public String process(String story) {
        List<ChatMessage> messages = List.of(
                new SystemMessage("You are an expert literary analyzer. " +
                        "Extract all distinct character names from the provided story text. " +
                        "Exclude titles (e.g., 'Doctor', 'Captain') unless integral to the name (e.g., 'Queen Elizabeth'). " +
                        "The characters should be the primary actors/drivers of the story. To resolve Ambiguous references, resolve pronouns using context, (e.g., 'He [John] laughed')." +
                        " Return ONLY the JSON array/object, without any markdown code blocks, backticks, or extra text." +
                        " The list should be sorted according to the importance of the character to the story with one having the highes impact in the story being at the top"),
                new UserMessage("Story: \n" + story)
        );
        return model.chat(messages).aiMessage().text();
        // return Arrays.asList(response.split(","));
    }
}
