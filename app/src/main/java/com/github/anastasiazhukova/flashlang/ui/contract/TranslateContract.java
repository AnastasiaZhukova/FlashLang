package com.github.anastasiazhukova.flashlang.ui.contract;

import com.github.anastasiazhukova.flashlang.ui.presenter.BasePresenter;

public interface TranslateContract {

    interface View {

        void onTranslateSuccess(String pTranslatedText);

        void onTranslateError(String pErrorMessage);

        String getSourceLanguage();

        String getSourceText();

        String getTargetLanguage();

    }

    interface Presenter extends BasePresenter<TranslateContract.View> {

        void translate();
    }

}
