package com.github.anastasiazhukova.lib.threading;

import com.github.anastasiazhukova.lib.threading.executors.AsyncTaskExecutor;
import com.github.anastasiazhukova.lib.threading.executors.ExecutorServiceExecutor;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;
import com.github.anastasiazhukova.lib.threading.publisher.IPublisher;

interface IExecutorFactory {

    IExecutor createAsyncTaskExecutor(AsyncTaskExecutor.Config pConfig);

    IExecutor createExecutorServiceExecutor(IPublisher pPublisher, ExecutorServiceExecutor.Config pConfig);

    IExecutor createThreadExecutor(IPublisher pPublisher);
}
