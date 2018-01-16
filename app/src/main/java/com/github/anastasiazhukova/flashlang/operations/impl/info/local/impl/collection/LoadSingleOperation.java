package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.collection;

import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadSingleOperation;

public class LoadSingleOperation extends AbstractLoadSingleOperation<Collection> {

    public LoadSingleOperation(final Selector[] pSelectors) {
        super(pSelectors);
    }

    @Override
    protected String getTableName() {
        return Collection.DbKeys.TABLE_NAME;
    }

    @Override
    protected ICursorConverter<Collection> getCursorConverter() {
        return new Collection.CursorConverter();
    }
}
