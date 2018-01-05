package com.github.anastasiazhukova.lib.logs;

public final class Log {

    //TODO add config to enable/disable logs
    public static void d(final String pTag, final String pMessage) {
        android.util.Log.d(pTag, pMessage);
    }

    public static void e(final String pTag, final String pMessage) {
        android.util.Log.e(pTag, pMessage);
    }

    public static void i(final String pTag, final String pMessage) {
        android.util.Log.i(pTag, pMessage);
    }

}
