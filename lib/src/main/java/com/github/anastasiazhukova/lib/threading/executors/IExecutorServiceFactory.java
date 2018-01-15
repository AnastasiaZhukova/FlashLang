package com.github.anastasiazhukova.lib.threading.executors;

import java.util.concurrent.ExecutorService;

interface IExecutorServiceFactory {

    ExecutorService getExecutorService(IExecutorServiceConfig pConfig);

}
