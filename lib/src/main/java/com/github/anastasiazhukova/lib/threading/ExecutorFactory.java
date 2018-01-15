package com.github.anastasiazhukova.lib.threading;

import com.github.anastasiazhukova.lib.threading.executors.AsyncTaskExecutor;
import com.github.anastasiazhukova.lib.threading.executors.ExecutorServiceExecutor;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;
import com.github.anastasiazhukova.lib.threading.executors.ThreadExecutor;
import com.github.anastasiazhukova.lib.threading.publisher.IPublisher;

class ExecutorFactory implements IExecutorFactory {

    @Override
    public IExecutor createAsyncTaskExecutor(final AsyncTaskExecutor.Config pConfig) {
        return new AsyncTaskExecutor(pConfig);
    }

    @Override
    public IExecutor createExecutorServiceExecutor(final IPublisher pPublisher, final ExecutorServiceExecutor.Config pConfig) {
        return new ExecutorServiceExecutor(pPublisher, pConfig);
    }

    @Override
    public IExecutor createThreadExecutor(final IPublisher pPublisher) {
        return new ThreadExecutor(pPublisher);
    }

}
