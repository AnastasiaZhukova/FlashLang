package com.github.anastasiazhukova.lib.threading.executors;

import com.github.anastasiazhukova.lib.threading.IExecutedCallback;
import com.github.anastasiazhukova.lib.threading.command.ICommand;
import com.github.anastasiazhukova.lib.threading.publisher.IPublisher;

import java.util.List;

public class ExecutionPool implements Runnable {

    private final IPublisher mPublisher;
    private final List<ICommand> mCommands;
    private IExecutedCallback mExecutedCallback;

    ExecutionPool(final IPublisher pPublisher, final List<ICommand> pCommands) {
        mPublisher = pPublisher;
        mCommands = pCommands;

    }

    ExecutionPool(final IPublisher pPublisher, final List<ICommand> pCommands, final IExecutedCallback pExecutedCallback) {
        mPublisher = pPublisher;
        mCommands = pCommands;
        mExecutedCallback = pExecutedCallback;
    }

    @Override
    public void run() {
        for (final ICommand command :
                mCommands) {
            try {
                final Object result = command.getOperation().perform();
                if (mPublisher != null) {
                    mPublisher.publishResult(result, command.getCallback());
                }
            } catch (final Exception pE) {
                if (mPublisher != null) {
                    mPublisher.publishError(pE, command.getCallback());
                }
            }
        }
        if (mPublisher != null) {
            mPublisher.publishExecuted(mExecutedCallback);
        }
    }
}
