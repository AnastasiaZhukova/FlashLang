package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.collection;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractLoadSingleOperation;
import com.github.anastasiazhukova.lib.contracts.ICallback;

public class LoadSingleOperation extends AbstractLoadSingleOperation<Collection> {

    public LoadSingleOperation(final ICallback<Collection> pCallback, final Selector pSelector) {
        super(pCallback, pSelector);
    }

    @Override
    protected String getTableName() {
        return Collection.DbKeys.TABLE_NAME;
    }

    @Override
    protected IDataSnapshotConverter<Collection> getDateSnapshotConverter() {
        return new Collection.DataSnapshotConverter();
    }
}
