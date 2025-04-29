package com.bheeshm.azure_ai_java.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scene {

    List<String> charactersInvolved;

    String sceneName;

    String sceneDescription;

    String sceneSetting;

}
