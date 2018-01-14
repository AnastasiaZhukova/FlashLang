package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class UploadUserToDbOperation implements IOperation<Void> {

    private final User mUser;

    UploadUserToDbOperation(final User pUser) {
        mUser = pUser;
    }

    @Override
    public Void perform() throws Exception {
        IDbTableConnector.Companion.getInstance().insert(mUser);
        return null;
    }
}
