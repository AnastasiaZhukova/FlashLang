package com.github.anastasiazhukova.lib.threading.publisher;

import com.github.anastasiazhukova.lib.contracts.ICallback;

public interface IPublisher {

    <Result> void publishResult(final Result pResult, final ICallback<Result> pCallback);

    void publishError(final Throwable pThrowable, final ICallback pCallback);

}
