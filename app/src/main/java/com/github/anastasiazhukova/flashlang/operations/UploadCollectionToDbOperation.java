package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class UploadCollectionToDbOperation implements IOperation<Void> {

    private final Collection mCollection;

    public UploadCollectionToDbOperation(final Collection pCollection) {
        mCollection = pCollection;
    }

    @Override
    public Void perform() throws Exception {
        IDbTableConnector.Companion.getInstance().insert(mCollection);
        IFirebaseDbConnector.Impl.Companion.getInstance().insert(mCollection);
        return null;
    }
}
