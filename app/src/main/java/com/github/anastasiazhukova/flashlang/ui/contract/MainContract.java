package com.github.anastasiazhukova.flashlang.ui.contract;

import com.github.anastasiazhukova.flashlang.ui.presenter.BasePresenter;

public interface MainContract {

    interface View {

    }

    interface Presenter extends BasePresenter<View> {

        void loadCurrentUser();
    }

}
