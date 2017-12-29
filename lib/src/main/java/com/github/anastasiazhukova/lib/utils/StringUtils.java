package com.github.anastasiazhukova.lib.utils;

public final class StringUtils {

    public static boolean isNullOrEmpty(final String pString) {
        return pString == null || pString.isEmpty();
    }

    public static boolean isNullOrEmpty(final CharSequence pCharSequence) {
        return pCharSequence == null || pCharSequence.length() <= 0;
    }

}
