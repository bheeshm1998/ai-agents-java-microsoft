package com.bheeshm.azure_ai_java.agent;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CharacterFeatureAgent2 {

    private final ChatLanguageModel model;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CharacterFeatureAgent2(ChatLanguageModel model) {
        this.model = model;
    }

    public String process(String story, List<String> characterNames) {


        List<ChatMessage> messages = List.of(
                new SystemMessage("""
                    You are a helpful assistant that extracts physical features of characters from a story.
                    ONLY generate features for the given character names. DO NOT invent or add extra characters.
                    If apart from the fields specified, a character has got any physical features that makes itself distinct, then store those distintictive features in the field specialCharacterisOfTheCharacter.
                    If any feature is not inferrable, try guessing that feature according to the context of the story. If still not inferrable then mark that feature as "N/A".
                    Respond STRICTLY in this JSON format without any markdown code blocks, backticks, or extra text.:
                    Example json
                    [
                      {
                        "name": "<character name>",
                        {
                            "physicalFeatures": {
                              "gender": "Female", // male, female, non-binary, transgender, neuter, N/A, etc.
                              "race": "Asian", // African, Black, Caucasian, White, Slavic, Hispanic, Indigenous, Middle Eastern, N/A, etc.
                              "height": "tall", // short, petite, average, tall, towering, N/A, etc.
                              "weightType": "Average", // underweight, lean, athletic, muscular, curvy, overweight, obese, N/A, etc.
                              "skinColor": "Light beige", // pale, fair, olive, tan, brown, dark brown, ebony, N/A, etc.
                              "noseTipShape": "Pointed", // rounded, upturned, broad, narrow, flat, hooked, N/A, etc.
                              "faceShape": "Oval", // round, square, heart-shaped, diamond, oblong, triangular, N/A, etc.
                              "hairColor": "Black", // blonde, brown, auburn, red, gray, white, dyed (specify color), N/A, etc.
                              "hairLength": "Shoulder-length", // bald, buzz cut, short, chin-length, long, waist-length, N/A, etc.
                              "hairTexture": "Straight", // wavy, curly, coiled, frizzy, kinky, braided, N/A, etc.
                              "eyeColor": "Brown", // blue, green, hazel, gray, amber, heterochromia (different colors), N/A, etc.
                              "lipThickness": "Medium", // thin, full, plump, uneven, N/A, etc.
                              "lipShape": "Full", // bow-shaped, downturned, wide, heart-shaped, N/A, etc.
                              "posture": "Upright", // slouched, hunched, relaxed, stiff, confident, N/A, etc.
                              "bodyShape": "Hourglass", // pear, apple, rectangle, inverted triangle, athletic, N/A, etc.
                              "ageGroup": "Young adult", // child, teen, adult, middle-aged, elderly, N/A, etc.
                              "eyebrowShape": "Arched", // straight, rounded, thick, thin, bushy, tapered, N/A, etc.
                              "jawline": "Soft", // sharp, square, rounded, weak, prominent, N/A, etc.
                              "religion": "Buddhist", // Christian, Muslim, Hindu, Jewish, Atheist, Agnostic, N/A, etc.
                              "ethinicity": "Japanese", // Chinese, Indian, Nigerian, Russian, Mexican, N/A, etc.
                              "earsShape": "Small lobes", // attached lobes, large lobes, pointed, protruding, N/A, etc.
                              "moodOfTheCharacter": "Calm", // angry, happy, sad, anxious, excited, neutral, N/A, etc.
                              "clothesDescription": "Wearing a blue kimono with floral patterns", // formal suit, casual jeans and t-shirt, traditional attire, N/A, etc.
                              "specialCharacterisOfTheCharacter": "Has a small mole under the left eye" // scars, tattoos, birthmarks, piercings, unique accessories, N/A, etc.
                            }
                          }
                      },
                      ...
                    ]
                    """),
                new UserMessage("Extract physical features for these characters: "
                        + String.join(", ", characterNames) + "\nStory:\n" + story)
        );

        String response = model.chat(messages).aiMessage().text();
        return response;

    }
}


