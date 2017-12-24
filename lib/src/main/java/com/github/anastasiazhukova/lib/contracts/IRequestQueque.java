package com.github.anastasiazhukova.lib.contracts;

public interface IRequestQueque<T extends IRequest> {

    T takeFirst() throws InterruptedException;

    void addFirst(final T pRequest);

}
