package com.github.anastasiazhukova.utilslib.contracts;

public interface IResponse<T> {

    T getResult();

    Throwable getError();
}
