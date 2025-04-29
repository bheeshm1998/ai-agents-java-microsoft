package com.bheeshm.azure_ai_java.agent;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CharacterFeatureAgent {

    private final ChatLanguageModel model;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CharacterFeatureAgent(ChatLanguageModel model) {
        this.model = model;
    }

    public String process(String story, List<String> characterNames) {


        List<ChatMessage> messages = List.of(
                new SystemMessage("""
                    You are a helpful assistant that extracts physical features of characters from a story.
                    ONLY generate features for the given character names. DO NOT invent or add extra characters.
                    If apart from the fields specified, a character has got any physical features that makes itself distinct, then store those distintictive features in the field specialCharacterisOfTheCharacter.
                    If any feature is not inferrable, try guessing the that feature according to the context of the story and make intelligent guesses. If still not inferrable then mark that feature as NOT_INFERRABLE.
                    Respond STRICTLY in this JSON format without any markdown code blocks, backticks, or extra text.:
                    
                    [
                      {
                        "name": "<character name>",
                        "physicalFeatures": {
                          "gender": "MALE|FEMALE|NEUTER|NOT_INFERRABLE",
                          "race": "ASIAN|CAUCASIAN|ARAB|SOUTH_ASIAN|AFRICAN|NOT_INFERRABLE",
                          "height": "SHORT|AVERAGE|TALL",
                          "weight": "UNDERWEIGHT|LEAN|AVERAGE|ATHLETIC|MUSCULAR|OVERWEIGHT|OBESE",
                          "skinColor": "VERY_FAIR|FAIR|MEDIUM|WHEATISH|BROWN|DARK_BROWN|VERY_DARK",
                          "noseTip": "POINTED|ROUNDED|BULBOUS|UPTURNED|DOWNTURNED|NOT_INFERRABLE",
                          "faceShape": "OVAL|ROUND|SQUARE|HEART_SHAPED|DIAMOND|NOT_INFERRABLE",
                          "hairColor": "BLACK|BROWN|BLONDE|RED|GRAY|WHITE|AUBURN|NOT_INFERRABLE",
                          "hairLength": "BALD|BUZZED|SHORT|MEDIUM|LONG|VERY_LONG|NOT_INFERRABLE",
                          "hairTexture": "STRAIGHT|WAVY|CURLY|COILED|KINKY|NOT_INFERRABLE",
                          "eyeColor": "BROWN|HAZEL|GREEN|BLUE|GRAY|AMBER|HETEROCHROMIA|NOT_INFERRABLE",
                          "lipThickness": "THIN| MEDIUM| THICK| FULL| NOT_INFERRABLE",
                          "lipShape": "CUPIDS_BOW|FLAT|WIDE|NARROW|HEART_SHAPED|NOT_INFERRABLE",
                          "posture": "UPRIGHT| SLOUCHED|FORWARD_LEANING|TILTED|HUNCHED",
                          "bodyShape": "ECTOMORPH|MESOMORPH|ENDOMORPH|PEAR_SHAPED|APPLE_SHAPED|HOURGLASS|RECTANGLE",
                          "specialCharacterisOfTheCharacter": "<optional free text>"
                        }
                      },
                      ...
                    ]
                    """),
                new UserMessage("Extract physical features for ONLY these characters: "
                        + String.join(", ", characterNames) + "\nStory:\n" + story)
        );

        String response = model.chat(messages).aiMessage().text();
        return response;

    }
}

