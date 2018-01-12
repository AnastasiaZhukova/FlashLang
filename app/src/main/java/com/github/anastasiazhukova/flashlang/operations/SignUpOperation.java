package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.firebase.auth.IFirebaseUserManager;
import com.github.anastasiazhukova.flashlang.firebase.auth.UserFactory;
import com.github.anastasiazhukova.flashlang.firebase.auth.request.AuthRequest;
import com.github.anastasiazhukova.flashlang.firebase.auth.request.EmailAuthInfo;
import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.google.firebase.auth.FirebaseUser;

public class SignUpOperation implements IOperation<Void> {

    private final String mEmail;
    private final String mPassword;
    private final ICallback<User> mCallback;

    public SignUpOperation(final String pEmail, final String pPassword, final ICallback<User> pCallback) {
        mEmail = pEmail;
        mPassword = pPassword;
        mCallback = pCallback;
    }

    @Override
    public Void perform() throws Exception {
        final IFirebaseUserManager userManager = IFirebaseUserManager.Impl.Companion.getInstance();
        final EmailAuthInfo authInfo = new EmailAuthInfo(mEmail, mPassword);
        final IAuthRequest request = new AuthRequest(authInfo);
        userManager.signUp(request, new IFirebaseUserManager.IAuthCallback() {

            @Override
            public void onSuccess(final FirebaseUser pUser) {
                final UserFactory factory = new UserFactory();
                final User user = factory.createUser(pUser);
                mCallback.onSuccess(user);
            }

            @Override
            public void onError(final Throwable pThrowable) {
                mCallback.onError(pThrowable);
            }
        });
        return null;
    }
}
