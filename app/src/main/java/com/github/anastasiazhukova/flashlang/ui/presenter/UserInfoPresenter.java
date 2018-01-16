package com.github.anastasiazhukova.flashlang.ui.presenter;

import com.github.anastasiazhukova.flashlang.UserManager;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.IAchievement;
import com.github.anastasiazhukova.flashlang.domain.models.user.IUser;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.firebase.auth.FirebaseUserManager;
import com.github.anastasiazhukova.flashlang.operations.Operations;
import com.github.anastasiazhukova.flashlang.ui.contract.UserInfoContract;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.logs.Log;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.command.Command;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

public class UserInfoPresenter implements UserInfoContract.Presenter {

    private static final String LOG_TAG = UserInfoPresenter.class.getSimpleName();
    private IExecutor mExecutor;

    private UserInfoContract.View mView;
    private User mUser;

    public UserInfoPresenter() {
        mExecutor = IThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.ASYNC_TASK);
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
        final IOperation<User> loadCurrentUser = Operations.newOperation()
                .common()
                .load()
                .currentUser();
        final ICallback<User> callback = new ICallback<User>() {

            @Override
            public void onSuccess(final User pUser) {
                publishUserInfo(pUser);
                mUser = pUser;
            }

            @Override
            public void onError(final Throwable pThrowable) {
                publishUserInfoLoadError(pThrowable);
            }
        };
        final Command<User> command = new Command<>(loadCurrentUser);
        command.setCallback(callback);
        mExecutor.execute(command);
    }

    @Override
    public void getAchievements() {
        final IOperation<Achievement> getAchievements = Operations.newOperation()
                .info()
                .local()
                .achievement()
                .loadSingle(new Achievement.ByOwnerIdSelector(UserManager.getCurrentUser().getId()));
        final ICallback<Achievement> callback = new ICallback<Achievement>() {

            @Override
            public void onSuccess(final Achievement pAchievement) {
                publishAchievement(pAchievement);
            }

            @Override
            public void onError(final Throwable pThrowable) {
                Log.e(LOG_TAG, "onError: ", pThrowable);
            }
        };

        Command<Achievement> command = new Command<>(getAchievements);
        command.setCallback(callback);
        mExecutor.execute(command);
    }

    @Override
    public void logout() {
        UserManager.setCurrentUser(null);
        FirebaseUserManager.Impl.Companion.getInstance().singOut();
    }

    private void publishUserInfo(final IUser pUser) {
        if (mView != null) {
            mView.onUserLoaded(pUser);
        }
    }

    private void publishAchievement(final IAchievement pAchievement) {
        if (mView != null) {
            mView.onAchievementLoaded(pAchievement);
        }
    }

    private void publishUserInfoLoadError(final Throwable pThrowable) {
        if (mView == null) {
            mView.onUserLoadError(pThrowable.getMessage());
        }
    }

}
