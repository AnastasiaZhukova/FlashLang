package com.github.anastasiazhukova.lib.threading.executors;

import com.github.anastasiazhukova.lib.Constants;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.logs.Log;
import com.github.anastasiazhukova.lib.threading.publisher.IPublisher;

import java.util.concurrent.ExecutorService;

public class ExecutorServiceExecutor implements IExecutor {

    private static final String LOG_TAG = ExecutorServiceExecutor.class.getSimpleName();

    private final Config mConfig;
    private final IPublisher mPublisher;

    public ExecutorServiceExecutor(final IPublisher pPublisher, final Config pConfig) {
        mPublisher = pPublisher;
        mConfig = pConfig;
    }

    @Override
    public <Result> void execute(final IOperation<Result> pOperation) {
        execute(pOperation, null);
    }

    @Override
    public <Result> void execute(final IOperation<Result> pOperation, final ICallback<Result> pCallback) {
        final Runnable command = new ExecutionCommand<>(mPublisher, pCallback, pOperation);
        final ExecutorService executorService = getExecutorService();
        if (executorService != null) {
            executorService.execute(command);
        }
    }

    private ExecutorService getExecutorService() {
        final IExecutorServiceFactory executorServiceFactory = new ExecutorServiceFactory();
        try {
            return executorServiceFactory.getExecutorService(mConfig);
        } catch (final Exception pE) {
            Log.e(LOG_TAG, "getExecutorService: ", pE);
            return null;
        }
    }

    public static class Config implements IExecutorServiceConfig {

        ExecutorServiceType mType = Constants.Executor.DEFAULT_EXECUTOR_SERVICE;
        int mMaxNumOfThreads = Constants.Executor.DEFAULT_NUM_OF_THREADS;

        public static Config getDefaultConfig() {
            return new Config();
        }

        public Config setExecutorType(final ExecutorServiceType pType) {
            mType = pType;
            return this;
        }

        public Config setMaxNumOfThreads(final int pNumOfThreads) {
            mMaxNumOfThreads = pNumOfThreads;
            return this;
        }

        @Override
        public ExecutorServiceType getType() {
            return mType;
        }

        @Override
        public int getNumOfThreads() {
            return mMaxNumOfThreads;
        }
    }

}