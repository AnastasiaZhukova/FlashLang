package com.github.anastasiazhukova.flashlang.operations.impl.auth;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class AuthOperationBuilder {

    IOperation<Void> signIn(final String pEmail, final String pPassword, final ICallback<User> pCallback) {
        return new SignInOperation(pEmail, pPassword, pCallback);
    }

    IOperation<Void> signUp(final String pEmail, final String pPassword, final ICallback<User> pCallback) {
        return new SignUpOperation(pEmail, pPassword, pCallback);
    }

}
