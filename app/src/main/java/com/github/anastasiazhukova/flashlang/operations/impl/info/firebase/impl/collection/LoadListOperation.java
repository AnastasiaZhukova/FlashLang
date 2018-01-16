package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.collection;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractLoadListOperation;
import com.github.anastasiazhukova.lib.contracts.ICallback;

import java.util.List;

public class LoadListOperation extends AbstractLoadListOperation<Collection> {

    public LoadListOperation(final ICallback<List<Collection>> pCallback, final Selector pSelector) {
        super(pCallback, pSelector);
    }

    @Override
    protected String getTableName() {
        return Collection.DbKeys.TABLE_NAME;
    }

    @Override
    protected IDataSnapshotConverter<Collection> getDataSnapshotConverter() {
        return new Collection.DataSnapshotConverter();
    }
}
