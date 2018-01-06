package com.github.anastasiazhukova.lib.threading.executors;

import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.publisher.IPublisher;

class ExecutionCommand<Result> implements Runnable {

    private final IPublisher mPublisher;
    private final ICallback<Result> mCallback;
    private final IOperation<Result> mOperation;

    ExecutionCommand(final IPublisher pPublisher, final ICallback<Result> pCallback, final IOperation<Result> pOperation) {
        mPublisher = pPublisher;
        mCallback = pCallback;
        mOperation = pOperation;
    }

    @Override
    public void run() {
        try {
            final Result result = mOperation.perform();
            if (mPublisher != null) {
                mPublisher.publishResult(result, mCallback);
            }
        } catch (final Exception pE) {
            if (mPublisher != null) {
                mPublisher.publishError(pE, mCallback);
            }
        }
    }
}
