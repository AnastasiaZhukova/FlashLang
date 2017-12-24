package com.github.anastasiazhukova.lib.contracts;

public interface IResponse<T> {

    T getResult();

    Throwable getError();
}
