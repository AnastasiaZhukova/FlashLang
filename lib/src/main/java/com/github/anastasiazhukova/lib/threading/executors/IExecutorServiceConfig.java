package com.github.anastasiazhukova.lib.threading.executors;

interface IExecutorServiceConfig {

    ExecutorServiceType getType();

    int getNumOfThreads();

}
