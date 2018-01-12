package com.github.anastasiazhukova.flashlang.ui.presenter;

import com.github.anastasiazhukova.flashlang.R;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.SignInOperation;
import com.github.anastasiazhukova.flashlang.operations.SignUpOperation;
import com.github.anastasiazhukova.flashlang.ui.contract.AuthContract;
import com.github.anastasiazhukova.flashlang.utils.ConnectionManager;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.logs.Log;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;
import com.github.anastasiazhukova.lib.utils.StringUtils;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import static com.github.anastasiazhukova.flashlang.utils.ResourceUtils.getString;

public class AuthPresenter implements AuthContract.Presenter {

    private static final String LOG_TAG = AuthPresenter.class.getSimpleName();

    private AuthContract.View mRootView;
    private SignInPresenter mSignInPresenter;
    private SignUpPresenter mSignUpPresenter;

    @Override
    public void attachView(final AuthContract.View pView) {
        mRootView = pView;
    }

    @Override
    public void detachView() {
        mRootView = null;
    }

    @Override
    public ISignInPresenter getSignInPresenter() {
        if (mSignInPresenter == null) {
            mSignInPresenter = new SignInPresenter();
        }
        return mSignInPresenter;
    }

    @Override
    public ISignUpPresenter getSignUpPresenter() {
        if (mSignUpPresenter == null) {
            mSignUpPresenter = new SignUpPresenter();
        }
        return mSignUpPresenter;
    }

    public class SignInPresenter implements ISignInPresenter {

        AuthContract.View.IAuthInfoView mView;

        @Override
        public void attachView(final AuthContract.View.IAuthInfoView pView) {
            mView = pView;
        }

        @Override
        public void detachView() {
            mView = null;
        }

        @Override
        public void signIn() {
            final String email = mView.getEmail();
            final String password = mView.getPassword();
            if (StringUtils.isNullOrEmpty(email) || StringUtils.isNullOrEmpty(password)) {
                mRootView.onAuthError(getString(R.string.wrong_auth_info_message));
            } else {
                final ICallback<User> callback = new AuthCallback();
                final IOperation signInOperation = new SignInOperation(email, password, callback);
                performOperation(signInOperation);
            }
        }

    }

    public class SignUpPresenter implements ISignUpPresenter {

        AuthContract.View.IAuthInfoView mView;

        @Override
        public void attachView(final AuthContract.View.IAuthInfoView pView) {
            mView = pView;
        }

        @Override
        public void detachView() {
            mView = null;
        }

        @Override
        public void signUp() {
            Log.d(LOG_TAG, "signUp() called");
            final String email = mView.getEmail();
            final String password = mView.getPassword();
            if (StringUtils.isNullOrEmpty(email) || StringUtils.isNullOrEmpty(password)) {
                mRootView.onAuthError(getString(R.string.wrong_auth_info_message));
            } else {
                final ICallback<User> callback = new AuthCallback();
                final IOperation signInOperation = new SignUpOperation(email, password, callback);
                performOperation(signInOperation);
            }
        }
    }

    private class AuthCallback implements ICallback<User> {

        @Override
        public void onSuccess(final User pUser) {
            mRootView.onAuthSuccess();
            Log.d(LOG_TAG, "onSuccess() called with: pUser = [" + pUser + "]");
        }

        @Override
        public void onError(final Throwable pThrowable) {
            final String message;
            if (pThrowable instanceof FirebaseAuthWeakPasswordException) {
                message = getString(R.string.weak_password_message);
            } else if (pThrowable instanceof FirebaseAuthInvalidCredentialsException) {
                message = getString(R.string.wrong_auth_info_message);
            } else if (pThrowable instanceof FirebaseAuthInvalidUserException) {
                message = getString(R.string.no_user_message);
            } else {
                message = getString(R.string.unknown_error);
            }
            mRootView.onAuthError(message);
            Log.d(LOG_TAG, "onError() called with: pThrowable = [" + pThrowable + "]");
        }
    }

    private void performOperation(final IOperation pOperation) {
        final boolean isNetworkAvailable = ConnectionManager.isNetworkAvailable();
        if (isNetworkAvailable) {
            final IExecutor executor = IThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD);
            executor.execute(pOperation);
        } else {
            mRootView.onAuthError(getString(R.string.no_connection_message));
        }
    }
}
