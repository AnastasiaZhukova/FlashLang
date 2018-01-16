package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractUploadOperation<Model extends IDbModel<String>> implements IOperation<Boolean> {

    private final Model mModel;

    public AbstractUploadOperation(final Model pModel) {
        mModel = pModel;
    }

    @Override
    public Boolean perform() throws Exception {
        boolean isInserted = IDbTableConnector.Companion.getInstance().insert(mModel);
        return isInserted;
    }
}
