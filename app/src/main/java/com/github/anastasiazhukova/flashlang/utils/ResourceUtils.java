package com.github.anastasiazhukova.flashlang.utils;

import android.content.Context;

import com.github.anastasiazhukova.lib.context.ContextHolder;

public final class ResourceUtils {

    public static String getString(final int pResId) {
        final Context context = ContextHolder.getContext();
        final String string = context.getString(pResId);
        return string;
    }

}
