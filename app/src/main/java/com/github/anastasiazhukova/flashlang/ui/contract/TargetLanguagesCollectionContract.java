package com.github.anastasiazhukova.flashlang.ui.contract;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.ui.presenter.BasePresenter;

public interface TargetLanguagesCollectionContract {

    interface View {

        String getSourceLanguageKey();

        void onLoaded(Cursor pCursor);

        void onError(String pErrorMessage);

    }

    interface Presenter extends BasePresenter<TargetLanguagesCollectionContract.View> {

        void load();
    }

}
