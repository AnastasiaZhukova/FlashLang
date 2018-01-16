package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.collection;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractDeleteOperation;

public class DeleteOperation extends AbstractDeleteOperation<Collection> {

    public DeleteOperation(final Selector[] pSelectors) {
        super(pSelectors);
    }

    @Override
    protected String getTableName() {
        return Collection.DbKeys.TABLE_NAME;
    }
}
