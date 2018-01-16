package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.collection;

import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadListOperation;

public class LoadListOperation extends AbstractLoadListOperation<Collection> {

    public LoadListOperation(final String pGroupBy, final Selector[] pSelectors) {
        super(pGroupBy, pSelectors);
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
