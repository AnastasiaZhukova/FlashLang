package com.github.anastasiazhukova.lib.threadingTest;

import com.github.anastasiazhukova.lib.contracts.ICallback;

public class TestCallback implements ICallback<String> {

    private String mMessage;

    @Override
    public void onSuccess(final String pS) {
        mMessage = pS;
    }

    @Override
    public void onError(final Throwable pThrowable) {
        mMessage = pThrowable.getMessage();
    }

    public String getMessage() {
        return mMessage;
    }
}
