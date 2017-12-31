package com.github.anastasiazhukova.flashlang.api.utils;

import android.content.Context;

public final class LanguageUtils {

    public static String getLanguageName(final Context pContext, final String pLanguageKey) {
        final int languageId = pContext.getResources().getIdentifier(pLanguageKey, "string", pContext.getPackageName());
        final String languageName = pContext.getString(languageId);
        return languageName;
    }
}
