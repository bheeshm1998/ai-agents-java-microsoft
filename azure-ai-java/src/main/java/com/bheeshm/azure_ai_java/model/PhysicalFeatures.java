package com.bheeshm.azure_ai_java.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 public class PhysicalFeatures {
    Gender gender;

    Race race;

    Height height;

    WeightType weightType;

    SkinColor skinColor;

    NoseTipShape noseTip;

    FaceShape faceShape;

    HairColor hairColor;

    HairLength hairLength;

    HairTexture hairTexture;

    EyeColor eyeColor;

    LipThickness lipThickness;

    LipShape lipShape;

    Posture posture;

    BodyShape bodyShape;

    AgeGroup ageGroup;

    EyebrowShape eyebrowShape;

    Jawline jawline;

    Religion religion;

    Ethinicity ethinicity;

    EarsShape earsShape;

    Mood moodOfTheCharacter;

    String clothesDescription;

    String specialCharacterisOfTheCharacter;
}

enum Mood {
    HAPPY, SAD, ANGRY, EXCITED, CALM, CONFUSED, SCARED, SURPRISED, NEUTRAL
}

enum EarsShape {
    ATTACHED_LOBES, DETACHED_LOBES, POINTY, ROUNDED, PROTRUDING, NOT_INFERRABLE
}

enum AgeGroup {
    INFANT, CHILD, TEENAGER, YOUNG_ADULT, ADULT, MIDDLE_AGED, SENIOR, NOT_INFERRABLE
}

enum Ethinicity {
    ASIAN, CAUCASIAN, HISPANIC, AFRICAN_AMERICAN, MIDDLE_EASTERN, PACIFIC_ISLANDER, NOT_INFERRABLE
}

enum Jawline {
    STRAIGHT, SHARP, ROUND, SQUARE, NOT_INFERRABLE
}   

enum Religion {
    HINDU, MUSLIM, CHRISTIAN, SIKH, BUDDHIST, JEWISH, ATHEIST, AGNOSTIC, OTHER, NOT_INFERRABLE
}


enum Gender {
     MALE, FEMALE, NEUTER, NOT_INFERRABLE
}

enum Race {
     ASIAN, CAUCASIAN, ARAB, SOUTH_ASIAN, AFRICAN, NOT_INFERRABLE
}
 enum FaceShape {
    OVAL, ROUND, SQUARE, HEART_SHAPED, DIAMOND, NOT_INFERRABLE
}

 enum Forehead {
    BROAD, NARROW, HIGH, LOW, SLOPED, STRAIGHT, NOT_INFERRABLE
}

 enum HairColor {
    BLACK, BROWN, BLONDE, RED, GRAY, WHITE, AUBURN, NOT_INFERRABLE
}

 enum HairTexture {
    STRAIGHT, WAVY, CURLY, COILED, KINKY, NOT_INFERRABLE
}

 enum HairLength {
    BALD, BUZZED, SHORT, MEDIUM, LONG, VERY_LONG, NOT_INFERRABLE
}

 enum HairDensity {
    SPARSE, THIN, MEDIUM, THICK, VERY_THICK, NOT_INFERRABLE
}

 enum HairlinePattern {
    STRAIGHT, WIDOWS_PEAK, RECEDING, UNEVEN, BALD_SPOT, NOT_INFERRABLE
}

 enum EyebrowThickness {
    THIN, MEDIUM, THICK, BUSHY, NOT_INFERRABLE
}

 enum EyebrowShape {
    STRAIGHT, ROUNDED, ARCHED, ANGLED, UPWARD, DOWNWARD, NOT_INFERRABLE
}

 enum EyebrowGap {
    CLOSE, MEDIUM, WIDE, UNIBROW, NOT_INFERRABLE
}

 enum EyeColor {
    BROWN, HAZEL, GREEN, BLUE, GRAY, AMBER, HETEROCHROMIA, NOT_INFERRABLE
}

 enum EyeSize {
    SMALL, MEDIUM, LARGE, NOT_INFERRABLE
}

 enum EyeSpacing {
    CLOSE_SET, AVERAGE, WIDE_SET, NOT_INFERRABLE
}

 enum EyelidType {
    MONOLID, DOUBLE_EYELID, HOODED, DEEP_SET, PROTRUDING, NOT_INFERRABLE
}

 enum EyeShape {
    ALMOND, ROUND, HOODED, DOWNTURNED, UPTURNED, NOT_INFERRABLE
}

 enum NostrilFlare {
    MINIMAL, MODERATE, PRONOUNCED, NOT_INFERRABLE
}

 enum NoseTipShape {
    POINTED, ROUNDED, BULBOUS, UPTURNED, DOWNTURNED, NOT_INFERRABLE
}

 enum Cheekbones {
    FLAT, LOW, MEDIUM, HIGH, PROMINENT, NOT_INFERRABLE
}

 enum LipThickness {
    THIN, MEDIUM, THICK, FULL, NOT_INFERRABLE
}

 enum LipShape {
    CUPIDS_BOW, FLAT, WIDE, NARROW, HEART_SHAPED, NOT_INFERRABLE
}

 enum TeethAlignment {
    STRAIGHT, CROWDED, SPACED, CROOKED, NOT_INFERRABLE
}

 enum TeethColor {
    WHITE, OFF_WHITE, YELLOW, STAINED, NOT_INFERRABLE
}

 enum Chin {
    CLEFT, SQUARE, POINTED, RECEDING, ROUNDED, DIMPLED, NOT_INFERRABLE
}

// 2. EARS
 enum EarSize {
    SMALL, MEDIUM, LARGE, NOT_INFERRABLE
}

 enum EarPosition {
    LOW_SET, MEDIUM_SET, HIGH_SET, NOT_INFERRABLE
}

 enum EarShape {
    ATTACHED_LOBES, DETACHED_LOBES, POINTY, ROUNDED, PROTRUDING, NOT_INFERRABLE
}

// 3. SKIN
 enum SkinColor {
    VERY_FAIR, FAIR, MEDIUM, WHEATISH, BROWN, DARK_BROWN, VERY_DARK, NOT_INFERRABLE
}

 enum SkinTexture {
    SMOOTH, ROUGH, WRINKLED, ACNE_PRONE, FRECKLED, POCKMARKED
}

// 4. BODY
 enum Height {
    SHORT,  AVERAGE,  TALL, NOT_INFERRABLE
}

 enum WeightType {
    UNDERWEIGHT, LEAN, AVERAGE, ATHLETIC, MUSCULAR, OVERWEIGHT, OBESE, NOT_INFERRABLE
}

 enum BodyShape {
    ATHLETIC, LEAN, FAT, SKINNY, CHUBBY, NOT_INFERRABLE
}

 enum Posture {
    UPRIGHT, SLOUCHED, FORWARD_LEANING, TILTED, HUNCHED, NOT_INFERRABLE
}

 enum LimbProportions {
    BALANCED, LONG_ARMS, SHORT_ARMS, LONG_LEGS, SHORT_LEGS
}

// 5. HANDS AND FEET
 enum HandSize {
    SMALL, MEDIUM, LARGE
}

 enum FingerShape {
    SHORT, MEDIUM, LONG, STUBBY, SLENDER, CROOKED
}

 enum NailCharacteristics {
    BITTEN, MANICURED, LONG, SHORT, RIDGED, POLISHED, NATURAL
}

 enum FeetShape {
    FLAT, ARCHED, NEUTRAL
}

 enum ShoeSize {
    VERY_SMALL, SMALL, MEDIUM, LARGE, VERY_LARGE
}

// 6. MOVEMENT / GAIT
 enum WalkingStyle {
    NORMAL, LIMPING, SWAGGERING, BOUNCING, SHUFFLING, DRAGGING
}

 enum HandGestures {
    MINIMAL, MODERATE, EXPRESSIVE, FIDGETY, CONTROLLED
}

 enum HeadMovements {
    STEADY, NODDING, TILTING, SHAKING, JERKY
}

// 7. VOICE
 enum VoicePitch {
    VERY_HIGH, HIGH, MEDIUM, LOW, VERY_LOW
}

 enum VoiceTone {
    NASAL, RASPY, CLEAR, BREATHY, HUSKY, SMOOTH
}

 enum Accent {
    NONE, REGIONAL, FOREIGN, STRONG, SLIGHT
}

 enum SpeechPattern {
    NORMAL, STUTTERING, RAPID, SLOW, DRAWLING, MUMBLING
}

// 8. ODORS
 enum BodyOdor {
    NONE, MILD, MODERATE, STRONG, DISTINCTIVE
}

 enum FragranceType {
    NONE, PERFUME, COLOGNE, DEODORANT, NATURAL
}

// 9. CLOTHING AND ACCESSORIES
 enum ClothingStyle {
    FORMAL, BUSINESS_CASUAL, CASUAL, TRENDY, TRADITIONAL, SPORTY, ALTERNATIVE
}

 enum ColorPreference {
    NEUTRAL, BRIGHT, DARK, PASTEL, MONOCHROME, VARIED
}

 enum GlassesType {
    NONE, READING, PRESCRIPTION, SUNGLASSES, COSMETIC
}

 enum JewelryAmount {
    NONE, MINIMAL, MODERATE, ABUNDANT
}

// 10. SPECIAL CASES
 enum Prosthetics {
    NONE, LIMB, EYE, DENTAL, OTHER
}

 enum MedicalAids {
    NONE, HEARING_AID, BRACES, CRUTCHES, WHEELCHAIR, CANE, OTHER
}

 enum Deformities {
    NONE, BIRTH_DEFECT, AMPUTATION, INJURY_RELATED, OTHER
}
