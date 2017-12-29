package com.github.anastasiazhukova.lib.utils;

public class StringUtils {

    public static boolean isNullOrEmpty(final String pString) {
        return pString == null || pString.isEmpty();
    }

    public static boolean isNullOrEmpty(final CharSequence pStringBuilder) {
        return pStringBuilder == null || pStringBuilder.length() <= 0;
    }

}
