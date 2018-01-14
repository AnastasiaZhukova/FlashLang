package com.github.anastasiazhukova.flashlang.ui.presenter;

import android.graphics.drawable.Drawable;

import com.github.anastasiazhukova.flashlang.domain.models.user.IUser;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.LoadUserInfoOperation;
import com.github.anastasiazhukova.flashlang.operations.UploadUserImageOperation;
import com.github.anastasiazhukova.flashlang.ui.contract.UserInfoContract;
import com.github.anastasiazhukova.flashlang.utils.UiPublisher;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

public class UserInfoPresenter implements UserInfoContract.Presenter {

    private UserInfoContract.View mView;
    private User mUser;
    private UiPublisher mPublisher;

    public UserInfoPresenter() {
        mPublisher = new UiPublisher();
    }

    @Override
    public void attachView(final UserInfoContract.View pView) {
        mView = pView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getUser() {
        final IExecutor executor = ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD);
        final IOperation<User> operation = new LoadUserInfoOperation();
        executor.execute(operation, new ICallback<User>() {

            @Override
            public void onSuccess(final User pUser) {
                publishUserInfo(pUser);
                mUser = pUser;
            }

            @Override
            public void onError(final Throwable pThrowable) {
                publishUserInfoLoadError(pThrowable);
            }
        });
    }

    @Override
    public void uploadImage() {
        if (mView != null) {
            final Drawable image = mView.getImage();
            ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD)
                    .execute(new UploadUserImageOperation(mUser, image, new ICallback<Void>() {

                        @Override
                        public void onSuccess(final Void pVoid) {

                        }

                        @Override
                        public void onError(final Throwable pThrowable) {
                            publishImageLoadError(pThrowable);
                        }
                    }));

        }

    }

    private void publishUserInfo(final IUser pUser) {
        if (mView != null) {
            mView.onUserLoaded(pUser);
        }
    }

    private void publishUserInfoLoadError(final Throwable pThrowable) {
        if (mView == null) {
            mView.onUserLoadError(pThrowable.getMessage());
        }
    }

    private void publishImageLoadError(final Throwable pThrowable) {
        if (mView != null) {
            mPublisher.publish(new Runnable() {

                @Override
                public void run() {
                    mView.onImageLoadError(pThrowable.getMessage());
                }
            });
        }
    }

}
