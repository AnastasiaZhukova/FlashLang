package com.github.anastasiazhukova.lib.threading;

import android.os.Handler;
import android.os.Looper;

import com.github.anastasiazhukova.lib.threading.executors.AsyncTaskExecutor;
import com.github.anastasiazhukova.lib.threading.executors.ExecutorServiceExecutor;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;
import com.github.anastasiazhukova.lib.threading.publisher.Publisher;

public class ThreadingManager implements IThreadingManager {

    private final Config mConfig;
    private final IExecutorFactory mExecutorFactory;

    public ThreadingManager(final Config pConfig) {
        mExecutorFactory = new ExecutorFactory();
        mConfig = pConfig;
    }

    @Override
    public IExecutor getExecutor(final ExecutorType pType) {
        switch (pType) {
            case ASYNC_TASK:
                return createAsyncTask();
            case EXECUTOR_SERVICE:
                return createExecutorService();
            case THREAD:
                return createThread();
            default:
                return null;
        }
    }

    private IExecutor createAsyncTask() {
        final IExecutor executor = mExecutorFactory.createAsyncTaskExecutor(mConfig.mAsyncTaskConfig);
        return executor;
    }

    private IExecutor createExecutorService() {
        final IExecutor executor = mExecutorFactory.createExecutorServiceExecutor(new Publisher(mConfig.mHandler),
                mConfig.mExecutorServiceConfig);
        return executor;
    }

    private IExecutor createThread() {
        final IExecutor executor = mExecutorFactory.createThreadExecutor(new Publisher(mConfig.mHandler));
        return executor;
    }

    public static class Config {

        private Handler mHandler = new Handler(Looper.getMainLooper());
        private AsyncTaskExecutor.Config mAsyncTaskConfig = AsyncTaskExecutor.Config.getDefaultConfig();
        private ExecutorServiceExecutor.Config mExecutorServiceConfig = ExecutorServiceExecutor.Config.getDefaultConfig();

        public static Config getDefaultConfig() {
            return new Config();
        }

        public Config setHandler(final Handler pHandler) {
            mHandler = pHandler;
            return this;
        }

        public Config setAsyncTaskConfig(final AsyncTaskExecutor.Config pConfig) {
            mAsyncTaskConfig = pConfig;
            return this;
        }

        public Config setExecutorServiceConfig(final ExecutorServiceExecutor.Config pConfig) {
            mExecutorServiceConfig = pConfig;
            return this;
        }

    }

}
