package com.github.anastasiazhukova.flashlang.ui.contract;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.ui.presenter.BasePresenter;

public interface SourceLanguagesCollectionContract {

    interface View {

        void onLoaded(Cursor pCursor);

        void onError(String pErrorMessage);
    }

    interface Presenter extends BasePresenter<SourceLanguagesCollectionContract.View> {

        void load();
    }

}
