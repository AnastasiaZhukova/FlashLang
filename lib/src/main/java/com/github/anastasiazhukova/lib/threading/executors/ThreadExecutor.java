package com.github.anastasiazhukova.lib.threading.executors;

import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.publisher.IPublisher;

public class ThreadExecutor implements IExecutor {

    private final IPublisher mPublisher;

    public ThreadExecutor(final IPublisher pPublisher) {
        mPublisher = pPublisher;
    }

    @Override
    public <Result> void execute(final IOperation<Result> pOperation) {
        execute(pOperation, null);
    }

    @Override
    public <Result> void execute(final IOperation<Result> pOperation, final ICallback<Result> pCallback) {
        final Runnable command = new ExecutionCommand<>(mPublisher, pCallback, pOperation);
        final Thread thread = new Thread(command);
        thread.start();
    }
}
