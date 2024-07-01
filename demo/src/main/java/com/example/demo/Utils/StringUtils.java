package com.example.demo.Utils;

public class StringUtils {

    public static boolean isValidString(String str) {
        return str != null && !str.isEmpty() && str.length() > 0;
    }

}
