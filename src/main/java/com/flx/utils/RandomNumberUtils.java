package com.flx.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomNumberUtils {

    private static final String BASE_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private RandomNumberUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getRandomString(int length) {
        Random random = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(BASE_STR.length());
            builder.append(BASE_STR.charAt(number));
        }
        return builder.toString();
    }

}
