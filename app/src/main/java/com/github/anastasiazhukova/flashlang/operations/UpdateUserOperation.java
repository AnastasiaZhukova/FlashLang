package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class UpdateUserOperation implements IOperation<Void> {

    private final User mUser;

    public UpdateUserOperation(final User pUser) {
        mUser = pUser;
    }

    @Override
    public Void perform() throws Exception {
        final Selector byIdSelector = new User.ByIdSelector(mUser.getId());
        IDbTableConnector.Companion.getInstance().update(mUser, byIdSelector);
        IFirebaseDbConnector.Impl.Companion.getInstance().update(mUser, byIdSelector);
        return null;
    }
}
