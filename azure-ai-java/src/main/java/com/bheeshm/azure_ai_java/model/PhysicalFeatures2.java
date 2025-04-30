package com.bheeshm.azure_ai_java.model;

import com.bheeshm.azure_ai_java.utils.RandomUtils;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 public class PhysicalFeatures2 {

    String gender;

    String race;

    String height;

    String weightType;

    String skinColor;

    String noseTipShape;

    String faceShape;

    String hairColor;

    String hairLength;

    String hairTexture;

    String eyeColor;

    String lipThickness;

    String lipShape;

    String posture;

    String bodyShape;

    String ageGroup;

    String eyebrowShape;

    String jawline;

    String religion;

    String ethinicity;

    String earsShape;

    String moodOfTheCharacter;

    String clothesDescription;

    String specialCharacterisOfTheCharacter;


   public String generateDescription() {
      StringBuilder description = new StringBuilder();

      addFeature(description, "Gender", gender);
      addFeature(description, "Race", race);
      addFeature(description, "Height", height);
      addFeature(description, "Weight Type", weightType);
      addFeature(description, "Skin Color", skinColor);
      addFeature(description, "Nose Tip Shape", noseTipShape);
      addFeature(description, "Face Shape", faceShape);
      addFeature(description, "Hair Color", hairColor);
      addFeature(description, "Hair Length", hairLength);
      addFeature(description, "Hair Texture", hairTexture);
      addFeature(description, "Eye Color", eyeColor);
      addFeature(description, "Lip Thickness", lipThickness);
      addFeature(description, "Lip Shape", lipShape);
      addFeature(description, "Posture", posture);
      addFeature(description, "Body Shape", bodyShape);
      addFeature(description, "Age Group", ageGroup);
      addFeature(description, "Eyebrow Shape", eyebrowShape);
      addFeature(description, "Jawline", jawline);
      addFeature(description, "Religion", religion);
      addFeature(description, "Ethinicity", ethinicity);
      addFeature(description, "Ear Shape", earsShape);
      addFeature(description, "Mood", moodOfTheCharacter);
      addFeature(description, "Clothes", clothesDescription);
      addFeature(description, "Special Characteristics", specialCharacterisOfTheCharacter);

      // Removes the last comma and space if present for a cleaner look
      if (description.length() > 2) {
         description.setLength(description.length()); // Keep as is, or add custom ending.
      }
      return description.toString();
   }

   private void addFeature(StringBuilder sb, String featureName, String value) {
      if (value != null && !value.equals("N/A") && !value.trim().isEmpty()) {
         sb.append(featureName).append(": ").append(value).append(". ");
      }
   }

   public static void fillMissingFields(List<Character> characters) {
      for (Character character : characters) {
         PhysicalFeatures2 features = character.getPhysicalFeatures();

         // Gender
         if (features.getGender() == null || features.getGender().isEmpty() || features.getGender().equals("N/A"))
            features.setGender(RandomUtils.randomEnum(Gender.class).name());

         // Race
         if (features.getRace() == null || features.getRace().isEmpty() || features.getRace().equals("N/A"))
            features.setRace(RandomUtils.randomEnum(Race.class).name());

         // Height
         if (features.getHeight() == null || features.getHeight().isEmpty() || features.getHeight().equals("N/A"))
            features.setHeight(RandomUtils.randomEnum(Height.class).name());

         // WeightType
         if (features.getWeightType() == null || features.getWeightType().isEmpty() || features.getWeightType().equals("N/A"))
            features.setWeightType(RandomUtils.randomEnum(WeightType.class).name());

         // SkinColor
         if (features.getSkinColor() == null || features.getSkinColor().isEmpty() || features.getSkinColor().equals("N/A"))
            features.setSkinColor(RandomUtils.randomEnum(SkinColor.class).name());

         // NoseTipShape
         if (features.getNoseTipShape() == null || features.getNoseTipShape().isEmpty() || features.getNoseTipShape().equals("N/A"))
            features.setNoseTipShape(RandomUtils.randomEnum(NoseTipShape.class).name());

         // FaceShape
         if (features.getFaceShape() == null || features.getFaceShape().isEmpty() || features.getFaceShape().equals("N/A"))
            features.setFaceShape(RandomUtils.randomEnum(FaceShape.class).name());

         // HairColor
         if (features.getHairColor() == null || features.getHairColor().isEmpty() || features.getHairColor().equals("N/A"))
            features.setHairColor(RandomUtils.randomEnum(HairColor.class).name());

         // HairLength
         if (features.getHairLength() == null || features.getHairLength().isEmpty() || features.getHairLength().equals("N/A"))
            features.setHairLength(RandomUtils.randomEnum(HairLength.class).name());

         // HairTexture
         if (features.getHairTexture() == null || features.getHairTexture().isEmpty() || features.getHairTexture().equals("N/A"))
            features.setHairTexture(RandomUtils.randomEnum(HairTexture.class).name());

         // EyeColor
         if (features.getEyeColor() == null || features.getEyeColor().isEmpty() || features.getEyeColor().equals("N/A"))
            features.setEyeColor(RandomUtils.randomEnum(EyeColor.class).name());

         // LipThickness
         if (features.getLipThickness() == null || features.getLipThickness().isEmpty() || features.getLipThickness().equals("N/A"))
            features.setLipThickness(RandomUtils.randomEnum(LipThickness.class).name());

         // LipShape
         if (features.getLipShape() == null || features.getLipShape().isEmpty() || features.getLipShape().equals("N/A"))
            features.setLipShape(RandomUtils.randomEnum(LipShape.class).name());

         // Posture
         if (features.getPosture() == null || features.getPosture().isEmpty() || features.getPosture().equals("N/A"))
            features.setPosture(RandomUtils.randomEnum(Posture.class).name());

         // BodyShape
         if (features.getBodyShape() == null || features.getBodyShape().isEmpty() || features.getBodyShape().equals("N/A"))
            features.setBodyShape(RandomUtils.randomEnum(BodyShape.class).name());

         // AgeGroup
         if (features.getAgeGroup() == null || features.getAgeGroup().isEmpty() || features.getAgeGroup().equals("N/A"))
            features.setAgeGroup(RandomUtils.randomEnum(AgeGroup.class).name());

         // EyebrowShape
         if (features.getEyebrowShape() == null || features.getEyebrowShape().isEmpty() || features.getEyebrowShape().equals("N/A"))
            features.setEyebrowShape(RandomUtils.randomEnum(EyebrowShape.class).name());

         // Jawline
         if (features.getJawline() == null || features.getJawline().isEmpty() || features.getJawline().equals("N/A"))
            features.setJawline(RandomUtils.randomEnum(Jawline.class).name());

         // Religion
         if (features.getReligion() == null || features.getReligion().isEmpty() || features.getReligion().equals("N/A"))
            features.setReligion(RandomUtils.randomEnum(Religion.class).name());

         // Ethnicity (Note: Corrected spelling from 'ethinicity')
         if (features.getEthinicity() == null || features.getEthinicity().isEmpty() || features.getEthinicity().equals("N/A"))
            features.setEthinicity(RandomUtils.randomEnum(Ethinicity.class).name());

         // EarsShape
         if (features.getEarsShape() == null || features.getEarsShape().isEmpty() || features.getEarsShape().equals("N/A"))
            features.setEarsShape(RandomUtils.randomEnum(EarsShape.class).name());

         // MoodOfTheCharacter
         if (features.getMoodOfTheCharacter() == null || features.getMoodOfTheCharacter().isEmpty() || features.getMoodOfTheCharacter().equals("N/A"))
            features.setMoodOfTheCharacter(RandomUtils.randomEnum(Mood.class).name());
      }
   }
}
