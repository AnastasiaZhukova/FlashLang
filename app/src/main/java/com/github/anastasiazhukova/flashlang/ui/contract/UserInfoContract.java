package com.github.anastasiazhukova.flashlang.ui.contract;

import android.graphics.drawable.Drawable;

import com.github.anastasiazhukova.flashlang.domain.models.user.IUser;
import com.github.anastasiazhukova.flashlang.ui.presenter.BasePresenter;

public interface UserInfoContract {

    interface View {

        Drawable getImage();

        void onUserLoaded(IUser pUser);

        void onUserLoadError(String pErrorMessage);

        void onImageLoadError(String pErrorMessage);

    }

    interface Presenter extends BasePresenter<UserInfoContract.View> {

        void getUser();

        void uploadImage();

    }

}
