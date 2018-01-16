package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractLoadSingleOperation;
import com.github.anastasiazhukova.lib.contracts.ICallback;

public class LoadSingleOperation extends AbstractLoadSingleOperation<User> {

    public LoadSingleOperation(final ICallback<User> pCallback, final Selector pSelector) {
        super(pCallback, pSelector);
    }

    @Override
    protected String getTableName() {
        return User.DbKeys.TABLE_NAME;
    }

    @Override
    protected IDataSnapshotConverter<User> getDateSnapshotConverter() {
        return new User.DataSnapshotConverter();
    }
}
