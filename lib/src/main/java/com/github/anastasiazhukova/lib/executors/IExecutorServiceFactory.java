package com.github.anastasiazhukova.lib.executors;

import java.util.concurrent.ExecutorService;

public interface IExecutorServiceFactory {

    ExecutorService createFixedThreadExecutor(final int pNumOfThreads);

}
