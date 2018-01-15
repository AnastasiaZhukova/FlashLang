package com.github.anastasiazhukova.lib.contracts;

public interface ICallback<Result> {

    void onSuccess(Result pResult);

    void onError(Throwable pThrowable);

}
