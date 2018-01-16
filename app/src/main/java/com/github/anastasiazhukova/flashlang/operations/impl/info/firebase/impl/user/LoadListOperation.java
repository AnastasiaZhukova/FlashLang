package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractLoadListOperation;
import com.github.anastasiazhukova.lib.contracts.ICallback;

import java.util.List;

public class LoadListOperation extends AbstractLoadListOperation<User> {

    public LoadListOperation(final ICallback<List<User>> pCallback, final Selector pSelector) {
        super(pCallback, pSelector);
    }

    @Override
    protected String getTableName() {
        return User.DbKeys.TABLE_NAME;
    }

    @Override
    protected IDataSnapshotConverter<User> getDataSnapshotConverter() {
        return new User.DataSnapshotConverter();
    }
}
