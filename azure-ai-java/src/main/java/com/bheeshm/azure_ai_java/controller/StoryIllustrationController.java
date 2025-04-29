package com.bheeshm.azure_ai_java.controller;

import com.bheeshm.azure_ai_java.service.StoryIllustrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/story")
public class StoryIllustrationController {

    private final StoryIllustrationService storyIllustrationService;

    @Autowired
    public StoryIllustrationController(StoryIllustrationService storyIllustrationService) {
        this.storyIllustrationService = storyIllustrationService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/illustrate")
    public Map<String, String> illustrateStory(@RequestBody Map<String, Object> request) {
        String story = (String) request.get("story");
        int numberOfEvents = (int) request.getOrDefault("numberOfEvents", 1);
        String artStyle = (String) request.get("artStyle");
        String markdownStory = storyIllustrationService.processStory(story, numberOfEvents, artStyle);
        Map<String, String> map = new HashMap<>();
        map.put("markdown", markdownStory);
        return map;
    }

    @PostMapping("/illustrate-and-save-pdf")
    public ResponseEntity<Resource> illustrateStoryAndSaveAsPdf(@RequestBody Map<String, Object> request) {
        String story = (String) request.get("story");
        int numberOfScenes = (int) request.getOrDefault("numberOfScenes", 1);
        String artStyle = (String) request.get("artStyle");

        // Assume this now returns the path to the saved PDF file
        String pdfFilePath = storyIllustrationService.processStory(story, numberOfScenes, artStyle);

        try {
            // Create a Path object
            Path path = Paths.get(pdfFilePath);

            // Create a ByteArrayResource from the file
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            // Create headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFilePath);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

            // Return the ResponseEntity with the file
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(path.toFile().length())
                    .body(resource);

        } catch (IOException e) {
            // Handle exceptions
            return ResponseEntity.internalServerError().build();
        }
    }
}