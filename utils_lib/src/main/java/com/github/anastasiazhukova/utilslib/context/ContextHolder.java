package com.github.anastasiazhukova.utilslib.context;

import android.content.Context;

public final class ContextHolder {

    private static Context sContext;

    private ContextHolder() {
    }

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(final Context pContext) {
        sContext = pContext;
    }

}
