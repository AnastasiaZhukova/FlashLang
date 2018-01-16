package com.github.anastasiazhukova.flashlang.ui.contract;

import com.github.anastasiazhukova.flashlang.ui.presenter.BasePresenter;

import java.util.List;

public interface ImageSampleContract {

    interface View {

        void onError(String pErrorMessage);
    }

    interface Presenter extends BasePresenter<View> {

        List<String> loadData();
    }

}
