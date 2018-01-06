package com.github.anastasiazhukova.lib.threading.publisher;

import android.os.Handler;
import android.os.Looper;

import com.github.anastasiazhukova.lib.contracts.ICallback;

public class Publisher implements IPublisher {

    private final Handler mHandler;

    public Publisher() {
        this(new Handler(Looper.getMainLooper()));
    }

    public Publisher(final Handler pHandler) {
        mHandler = pHandler;
    }

    @Override
    public <Result> void publishResult(final Result pResult, final ICallback<Result> pCallback) {
        post(new Runnable() {

            @Override
            public void run() {
                pCallback.onSuccess(pResult);
            }
        });

    }

    @Override
    public void publishError(final Throwable pThrowable, final ICallback pCallback) {

        post(new Runnable() {

            @Override
            public void run() {
                pCallback.onError(pThrowable);
            }
        });

    }

    private void post(final Runnable pRunnable) {
        mHandler.post(pRunnable);
    }
}
