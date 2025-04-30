package com.bheeshm.azure_ai_java.utils;

import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        T[] values = clazz.getEnumConstants();
        return values[random.nextInt(values.length)];
    }
}

