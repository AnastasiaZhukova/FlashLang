package com.github.anastasiazhukova.lib.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceFactory implements IExecutorServiceFactory {

    @Override
    public ExecutorService createFixedThreadExecutor(final int pNumOfThreads) {
        if (pNumOfThreads <= 0) {
            throw new IllegalStateException("Wrong num of threads");
        }

        return Executors.newFixedThreadPool(pNumOfThreads);
    }
}
