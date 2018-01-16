package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractDeleteOperation<Model extends IDbModel<String>> implements IOperation<Void> {

    private final Selector mSelector;

    public AbstractDeleteOperation(final Selector pSelector) {
        mSelector = pSelector;
    }

    @Override
    public Void perform() throws Exception {
        IFirebaseDbConnector.Impl.Companion.getInstance().delete(getTableName(), mSelector);
        return null;
    }

    protected abstract String getTableName();
}
