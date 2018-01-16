package com.github.anastasiazhukova.lib.threading.command;

import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public interface ICommand<T> {

    IOperation<T> getOperation();

    ICallback<T> getCallback();

}
