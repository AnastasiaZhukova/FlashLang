package com.github.anastasiazhukova.lib.threading.executors;

import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public interface IExecutor {

    <Result> void execute(IOperation<Result> pOperation);

    <Result> void execute(IOperation<Result> pOperation, ICallback<Result> pCallback);
}
