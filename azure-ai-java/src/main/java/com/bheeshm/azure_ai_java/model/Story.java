package com.bheeshm.azure_ai_java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Story {
    
    @JsonProperty("title")
    String title;

    @JsonProperty("author")
    String author;

    @JsonProperty("content")
    String content;
}
