package com.bheeshm.azure_ai_java.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DalleImageGenerator {

    private final String endpointUrl;
    private final String apiKey;
    private final HttpClient httpClient;

    public DalleImageGenerator(String apiKey, String endpointUrl) {
        this.apiKey = apiKey;
        this.endpointUrl = endpointUrl;
        this.httpClient = HttpClient.newHttpClient();
    }

    public String generateImage(String prompt) {
        try {
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("prompt", prompt);
            requestMap.put("n", 1);
            requestMap.put("size", "1024x1024");
            requestMap.put("model", "dall-e-3");
            requestMap.put("quality", "standard");
            requestMap.put("response_format", "url");
//            requestMap.put("output_format", "png");

            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(requestMap);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpointUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey) // Changed header to use Authorization bearer token
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String body = response.body();
                // Extract image URL from the OpenAI response body
                int urlStart = body.indexOf("https://");
                int urlEnd = body.indexOf("\"", urlStart);
                return body.substring(urlStart, urlEnd);
            } else {
                throw new RuntimeException("Failed to generate image: " + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error generating image", e);
        }
    }
}


