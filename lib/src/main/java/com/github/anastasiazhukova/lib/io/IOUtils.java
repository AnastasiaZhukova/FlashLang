package com.github.anastasiazhukova.lib.io;

import com.github.anastasiazhukova.lib.logs.Log;

import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;

public final class IOUtils {

    private static final String LOG_TAG = IOUtils.class.getSimpleName();

    public static void close(final Closeable pCloseable) {
        if (pCloseable != null) {
            try {
                pCloseable.close();
            } catch (final IOException pE) {
                Log.e(LOG_TAG, pE.getMessage());
            }
        }
    }

    public static void disconnect(final HttpURLConnection pConnection) {
        if (pConnection != null) {
            pConnection.disconnect();
        }
    }
}
