package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public abstract class AbstractUploadAllOperation<Model extends IDbModel<String>> implements IOperation<Boolean> {

    private final Model[] mModels;

    public AbstractUploadAllOperation(final Model[] pModels) {
        mModels = pModels;
    }

    @Override
    public Boolean perform() throws Exception {
        final boolean isInserted = IDbTableConnector.Companion.getInstance()
                .insert(mModels);
        return isInserted;
    }
}
