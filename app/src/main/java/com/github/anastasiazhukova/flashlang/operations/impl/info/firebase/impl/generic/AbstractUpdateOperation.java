package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractUpdateOperation<Model extends IDbModel<String>> implements IOperation<Void> {

    private final Selector mSelector;
    private final Model mModel;

    public AbstractUpdateOperation(final Model pModel, final Selector pSelector) {
        mModel = pModel;
        mSelector = pSelector;
    }

    @Override
    public Void perform() throws Exception {
        IFirebaseDbConnector.Impl.Companion.getInstance()
                .update(mModel, mSelector);
        return null;
    }

}
