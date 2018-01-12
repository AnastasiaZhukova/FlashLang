package com.github.anastasiazhukova.flashlang.ui.presenter;

public interface BasePresenter<T> {

    void attachView(T pView);

    void detachView();

}
