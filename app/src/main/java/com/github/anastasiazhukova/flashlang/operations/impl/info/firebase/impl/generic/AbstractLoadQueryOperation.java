package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.google.firebase.database.Query;

public abstract class AbstractLoadQueryOperation<Model extends IDbModel<String>> implements IOperation<Query> {

    private final Selector mSelector;

    public AbstractLoadQueryOperation(final Selector pSelector) {
        mSelector = pSelector;
    }

    @Override
    public Query perform() throws Exception {
        final Query query = IFirebaseDbConnector.Impl.Companion.getInstance()
                .query()
                .tableName(getTableName())
                .selector(mSelector)
                .query();
        return query;
    }

    protected abstract String getTableName();
}
