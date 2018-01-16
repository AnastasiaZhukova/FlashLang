package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractUploadAllOperation;

public class UploadAllOperation extends AbstractUploadAllOperation<User> {

    public UploadAllOperation(final User[] pModels) {
        super(pModels);
    }
}
