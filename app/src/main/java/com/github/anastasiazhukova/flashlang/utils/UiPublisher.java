package com.github.anastasiazhukova.flashlang.utils;

import android.os.Handler;
import android.os.Looper;

public class UiPublisher {

    public void publish(final Runnable pTask) {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(pTask);
    }

}
