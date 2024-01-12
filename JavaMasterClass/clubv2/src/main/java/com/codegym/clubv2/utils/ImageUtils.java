package com.codegym.clubv2.utils;

import java.util.Base64;

public class ImageUtils {

    public static String encodeBase64(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}