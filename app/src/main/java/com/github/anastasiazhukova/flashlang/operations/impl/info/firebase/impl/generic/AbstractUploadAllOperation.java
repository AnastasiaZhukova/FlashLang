package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractUploadAllOperation<Model extends IDbModel<String>> implements IOperation<Void> {

    private final Model[] mModels;

    public AbstractUploadAllOperation(final Model[] pModels) {
        mModels = pModels;
    }

    @Override
    public Void perform() throws Exception {
        IFirebaseDbConnector.Impl.Companion.getInstance()
                .insert(mModels);
        return null;
    }
}
