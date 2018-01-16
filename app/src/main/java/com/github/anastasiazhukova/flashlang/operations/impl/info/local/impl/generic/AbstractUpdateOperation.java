package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractUpdateOperation<Model extends IDbModel<String>> implements IOperation<Boolean> {

    private final Selector[] mSelectors;
    private final Model mModel;

    public AbstractUpdateOperation(final Model pModel, final Selector[] pSelectors) {
        mModel = pModel;
        mSelectors = pSelectors;
    }

    @Override
    public Boolean perform() throws Exception {
        final boolean isUpdated = IDbTableConnector.Companion.getInstance()
                .update(mModel, mSelectors);
        return isUpdated;
    }

}
