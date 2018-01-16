package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.collection;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractLoadQueryOperation;

public class LoadQueryOperation extends AbstractLoadQueryOperation<Collection> {

    public LoadQueryOperation(final Selector pSelector) {
        super(pSelector);
    }

    @Override
    protected String getTableName() {
        return Collection.DbKeys.TABLE_NAME;
    }
}
