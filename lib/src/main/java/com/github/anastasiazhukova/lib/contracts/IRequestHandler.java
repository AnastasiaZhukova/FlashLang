package com.github.anastasiazhukova.lib.contracts;

public interface IRequestHandler<T extends IRequest> {

    void handle(T pRequest) throws Exception;

}
