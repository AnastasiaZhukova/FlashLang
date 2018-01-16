package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractDeleteOperation<Model extends IDbModel<String>> implements IOperation<Boolean> {

    private final Selector[] mSelectors;

    public AbstractDeleteOperation(final Selector[] pSelectors) {
        mSelectors = pSelectors;
    }

    @Override
    public Boolean perform() throws Exception {
        boolean isDeleted = IDbTableConnector.Companion.getInstance()
                .delete(getTableName(), mSelectors);
        return isDeleted;
    }

    protected abstract String getTableName();
}
