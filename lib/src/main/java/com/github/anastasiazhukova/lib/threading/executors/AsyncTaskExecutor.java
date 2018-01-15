package com.github.anastasiazhukova.lib.threading.executors;

import android.os.AsyncTask;

import com.github.anastasiazhukova.lib.Constants;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.logs.Log;

import java.util.concurrent.ExecutorService;

public class AsyncTaskExecutor implements IExecutor {

    private static final String LOG_TAG = AsyncTaskExecutor.class.getSimpleName();

    private final Config mConfig;

    public AsyncTaskExecutor(final Config pConfig) {
        mConfig = pConfig;
    }

    @Override
    public <Result> void execute(final IOperation<Result> pOperation) {
        execute(pOperation, null);
    }

    @Override
    public <Result> void execute(final IOperation<Result> pOperation, final ICallback<Result> pCallback) {
        final ExecutedAsyncTask<Result> executedAsyncTask = new ExecutedAsyncTask<>(pOperation, pCallback);
        final ExecutorService executorService = getExecutorService();
        if (executorService != null) {
            executedAsyncTask.executeOnExecutor(executorService);
        } else {
            executedAsyncTask.execute();
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

        ExecutorServiceType mType;
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

    private static class ExecutedAsyncTask<Result> extends AsyncTask<Void, Void, Object> {

        final IOperation<Result> mOperation;
        final ICallback<Result> mCallback;

        ExecutedAsyncTask(final IOperation<Result> pOperation, final ICallback<Result> pCallback) {
            mOperation = pOperation;
            mCallback = pCallback;
        }

        @Override
        protected Object doInBackground(final Void... pVoid) {
            try {
                return mOperation.perform();
            } catch (final Exception pE) {
                return pE;
            }
        }

        @Override
        protected void onPostExecute(final Object pO) {
            if (mCallback == null) {
                return;
            }
            if (pO == null) {
                mCallback.onError(new Exception("Can't get result"));
            } else if (pO instanceof Throwable) {
                mCallback.onError((Throwable) pO);
            } else {
                try {
                    //noinspection unchecked
                    final Result result = (Result) pO;
                    mCallback.onSuccess(result);
                } catch (final Exception pE) {
                    Log.e(LOG_TAG, "onPostExecute: ", pE);
                    mCallback.onError(new Exception("Can't get result"));
                }
            }
        }
    }

}
