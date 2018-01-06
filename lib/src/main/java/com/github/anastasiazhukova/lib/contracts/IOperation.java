package com.github.anastasiazhukova.lib.contracts;

public interface IOperation<Result> {

    Result perform() throws Exception;

}
