package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.collection;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadCursorOperation;

public class LoadCursorOperation extends AbstractLoadCursorOperation<Collection> {

    public LoadCursorOperation(final String pGroupBy, final Selector[] pSelectors) {
        super(pGroupBy, pSelectors);
    }

    @Override
    protected String getTableName() {
        return Collection.DbKeys.TABLE_NAME;
    }
}
