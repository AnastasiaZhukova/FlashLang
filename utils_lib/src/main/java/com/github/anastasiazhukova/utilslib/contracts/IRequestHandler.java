package com.github.anastasiazhukova.utilslib.contracts;

public interface IRequestHandler<T extends IRequest> {

    void handle(T pRequest) throws Exception;

}
