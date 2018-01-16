package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractUploadOperation;

public class UploadOperation extends AbstractUploadOperation<User> {

    public UploadOperation(final User pModel) {
        super(pModel);
    }
}
