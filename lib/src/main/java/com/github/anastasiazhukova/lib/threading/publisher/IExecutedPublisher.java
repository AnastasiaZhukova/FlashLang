package com.github.anastasiazhukova.lib.threading.publisher;

import com.github.anastasiazhukova.lib.threading.IExecutedCallback;

public interface IExecutedPublisher {

    void publishExecuted(IExecutedCallback pCallback);

}
