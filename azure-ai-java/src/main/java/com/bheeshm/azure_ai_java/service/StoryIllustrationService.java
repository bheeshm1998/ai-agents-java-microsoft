package com.bheeshm.azure_ai_java.service;

import com.bheeshm.azure_ai_java.agent.*;
import com.bheeshm.azure_ai_java.model.*;
import com.bheeshm.azure_ai_java.model.Character;
import com.bheeshm.azure_ai_java.utils.DalleImageGenerator;
import com.vladsch.flexmark.util.data.MutableDataSet;
import dev.langchain4j.model.azure.AzureOpenAiStreamingChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.html.HtmlRenderer;

@Service
public class StoryIllustrationService {


    private final ChatLanguageModel chatLanguageModel;
    private final DalleImageGenerator imageGenerator;
    private final ObjectMapper objectMapper;

    public StoryIllustrationService(
            ChatLanguageModel chatLanguageModel, DalleImageGenerator dalleImageGenerator) {
        this.chatLanguageModel = chatLanguageModel;
        this.imageGenerator = dalleImageGenerator;
        this.objectMapper = new ObjectMapper();
    }

    public String processStory(String story, int numberOfScenes, String artStyle) {
        // Instantiate agents
        StoryCleanupAgent storyCleanupAgent = new StoryCleanupAgent(chatLanguageModel);
        CharacterExtractionAgent characterExtractionAgent = new CharacterExtractionAgent(chatLanguageModel);
        EventExtractionAgent eventExtractionAgent = new EventExtractionAgent(chatLanguageModel);
        CharacterFeatureAgent characterFeatureAgent = new CharacterFeatureAgent(chatLanguageModel);
        CharacterEventMappingAgent characterEventMappingAgent = new CharacterEventMappingAgent(chatLanguageModel);
        StoryImageMarkdownAgent storyImageMarkdownAgent = new StoryImageMarkdownAgent(chatLanguageModel);
        SummarizerAgent summarizerAgent = new SummarizerAgent(chatLanguageModel);


        Story cleanedStory = null;

        try {
            int partLength = Math.min(story.length(), 500);
            String storyText = cleanJsonResponse(storyCleanupAgent.cleanStory(story.substring(0, partLength)));
            StringBuilder sb = new StringBuilder();
            cleanedStory = this.objectMapper.readValue(storyText,  Story.class);
            sb.append(cleanedStory.getContent()).append(story.substring(partLength, story.length()));
            cleanedStory.setContent(sb.toString());
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON to Story: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("[ storyCleanupAgent ] Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Waiting for 10 seconds before the next LLM call");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> characters = new ArrayList<>();
        try {
            String charactersText = cleanJsonResponse(characterExtractionAgent.process(cleanedStory.getContent()));
            characters = this.objectMapper.readValue(charactersText,  new TypeReference<List<String>>() {});
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON to List<String>: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("[ characterExtractionAgent ] Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Waiting for 10 seconds before the next LLM call");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

         List<Scene> scenes = new ArrayList<>();
        try {
            String scenesText = cleanJsonResponse(eventExtractionAgent.process(cleanedStory.getContent(), numberOfScenes, characters));
            scenes = objectMapper.readValue(scenesText, new TypeReference<List<Scene>>() {});;
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON to List<Character>: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("[ eventExtractionAgent ] Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Waiting for 10 seconds before the next LLM call");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

         List<Character> characterFeatures = new ArrayList<>();
        try {
            String charactersFeaturesText = cleanJsonResponse(characterFeatureAgent.process(cleanedStory.getContent(), characters));
            characterFeatures = objectMapper.readValue(charactersFeaturesText, new TypeReference<List<Character>>() {});
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON to List<Character>: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("[ characterFeatureAgent ] Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Waiting for 10 seconds before the next LLM call");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map<String, Character> characterMap = new HashMap<>();
        characterFeatures.forEach(character -> characterMap.put(character.getName(), character));

        Map<String, String> eventImages = new HashMap<>();
        System.out.println("number of total scenes for which image needs to be generated " + scenes.size());
        for (Scene scene : scenes) {
            System.out.println("Generating image for scene " + scene.getSceneName());
            List<String> charactersInvolved = scene.getCharactersInvolved(); // Assuming List<Character> not just names
            StringBuilder promptBuilder = new StringBuilder();

            // Start with the scene description
            promptBuilder.append(scene.getSceneDescription()).append(".\n\n");

            // Add character details
            for (String characterName : charactersInvolved) {
                promptBuilder.append(characterName).append(": ").append(buildCharacterDescription(characterMap.get(characterName))).append("\n");
            }

            // Add some basic scene setting (optional, customize if you have metadata)
            promptBuilder.append("\nScene setting: ").append(scene.getSceneSetting());

            promptBuilder.append(". Art style: ").append(artStyle);

            String imagePrompt = promptBuilder.toString();

            try {
                Thread.sleep(10000);
                String refinedImagePrompt = summarizerAgent.process(imagePrompt);
                Thread.sleep(10000);
                System.out.println("the refined prompt is " + refinedImagePrompt);
                String imageUrl = imageGenerator.generateImage(refinedImagePrompt);
                eventImages.put(scene.getSceneName(), imageUrl);
            } catch (Exception e) {
                System.out.println("Error in summarizerAgent: " + e.getMessage());
                e.printStackTrace();
            }

        }

        List<Image> imagesList = new ArrayList<>();
        eventImages.forEach((imageCaption, imageUrl) -> imagesList.add(new Image( imageUrl, imageCaption)));

        String markdownText = storyImageMarkdownAgent.process(cleanedStory.getContent(), imagesList);

        String currentDateTimestamp = new Date().toString();
        String markdownFilePathWithSpaces = cleanedStory.getTitle() + "_" + cleanedStory.getAuthor()+ currentDateTimestamp ;
        String finalMarkdownFilePath = markdownFilePathWithSpaces.replaceAll("[\\s\\W]+", "_") + ".md";

        try {
            saveMarkdownToFile(markdownText, finalMarkdownFilePath);
        } catch (Exception e) {
            System.out.println("Error occurred when saving the markdown file" + e.getMessage());
        }
        System.out.println("Markdowwn file saved successfully");
//        String pdfFilePathWithSpaces = cleanedStory.getTitle() + "_" + cleanedStory.getAuthor()+ currentDateTimestamp;
//        String finalPdfFilePath = pdfFilePathWithSpaces.replaceAll("[\\s\\W]+", "_") + ".pdf";
//        try{
//            convertMarkdownToPdf(finalMarkdownFilePath, finalPdfFilePath);
//        } catch (Exception e) {
//            System.out.println("Error occurred when saving the pdf file" + e.getMessage());
//        }



        // Prepare the response
//        Map<String, Object> response = new HashMap<>();
//        response.put("cleanedStory", cleanedStory);
//        response.put("characters", characters);
//         response.put("scenes", scenes);
//         response.put("characterFeatures", characterFeatures);
//         response.put("eventImages", eventImages);

//        return response;

        return markdownText;
    }

    public static String getCharactersNames(List<String> charactersList) {
        if (charactersList == null || charactersList.isEmpty()) {
            return "";
        }

        if (charactersList.size() == 1) {
            return charactersList.get(0);
        }

        // Join all elements except the last one with commas
        String allButLast = String.join(", ", charactersList.subList(0, charactersList.size() - 1));

        // Combine with " and " for the final element
        return allButLast + " and " + charactersList.get(charactersList.size() - 1);
    }

    private String buildCharacterDescription(Character character) {
        if(character == null) return "";
        PhysicalFeatures pf = character.getPhysicalFeatures();
        return String.format(
                "A %s %s %s with %s %s %s hair, %s skin, %s eyes, %s lips, and a %s face.",
                safeToString(pf.getHeight(), "average height"),
                safeToString(pf.getRace(), "unspecified race"),
                safeToString(pf.getGender(), "male"),
                safeToString(pf.getHairLength(), "medium"),
                safeToString(pf.getHairTexture(), "plain"),
                safeToString(pf.getHairColor(), "black"),
                safeToString(pf.getSkinColor(), "medium tone"),
                safeToString(pf.getEyeColor(), "brown"),
                safeToString(pf.getLipShape(), "normal"),
                safeToString(pf.getFaceShape(), "oval")
        );
    }

    private String safeToString(Object value, String defaultValue) {
        return (value != null && !value.toString().toLowerCase().equals("not_inferrable")) ? value.toString().toLowerCase().replace("_", " ") : defaultValue;
    }

    public static void saveMarkdownToFile(String markdownText, String filePath) throws Exception {
        Files.writeString(Path.of(filePath), markdownText, StandardCharsets.UTF_8);
    }

    public static void convertMarkdownToPdf(String markdownFilePath, String pdfFilePath) throws IOException, DocumentException {
        // Read markdown content
        String markdown = Files.readString(Path.of(markdownFilePath), StandardCharsets.UTF_8);

        // Convert markdown to HTML
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(markdown);
        String html = renderer.render(document);

        // Write HTML to PDF
        Document pdfDoc = new Document();
        PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfFilePath));
        pdfDoc.open();
        pdfDoc.add(new Paragraph(html)); // Note: This will dump raw HTML text

        // If you want better HTML rendering, you need an HTML-to-PDF library like Flying Saucer
        pdfDoc.close();
    }

    /**
     * Removes ```json wrapping from a JSON response if present.
     * @param jsonResponse The JSON response string that might be wrapped
     * @return Clean JSON string without ```json markers
     */
    public static String cleanJsonResponse(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }

        // Pattern to match ```json at start and ``` at end (with optional whitespace)
        Pattern pattern = Pattern.compile("^\\s*```json\\s*(.*?)\\s*```\\s*$", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(jsonResponse);

        if (matcher.matches()) {
            // If wrapped in ```json, return the inner content
            return matcher.group(1);
        }

        // Otherwise return the original string
        return jsonResponse.trim();
    }
}
