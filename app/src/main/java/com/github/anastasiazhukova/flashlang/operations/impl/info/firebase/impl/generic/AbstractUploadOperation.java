package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractUploadOperation<Model extends IDbModel<String>> implements IOperation<Void> {

    private final Model mModel;

    public AbstractUploadOperation(final Model pModel) {
        mModel = pModel;
    }

    @Override
    public Void perform() throws Exception {
        IFirebaseDbConnector.Impl.Companion.getInstance()
                .insert(mModel);
        return null;
    }
}
