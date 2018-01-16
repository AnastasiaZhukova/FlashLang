package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractLoadCursorOperation<Model extends IDbModel<String>> implements IOperation<Cursor> {

    private final String mGroupBy;
    private final Selector[] mSelectors;

    public AbstractLoadCursorOperation(final String pGroupBy, final Selector[] pSelectors) {
        mGroupBy = pGroupBy;
        mSelectors = pSelectors;
    }

    @Override
    public Cursor perform() throws Exception {
        final Cursor cursor = IDbTableConnector.Companion.getInstance()
                .get(getTableName(), mGroupBy, mSelectors);
        return cursor;
    }

    protected abstract String getTableName();
}
