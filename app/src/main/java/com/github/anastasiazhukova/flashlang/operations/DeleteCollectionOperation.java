package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class DeleteCollectionOperation implements IOperation<Void> {

    private final String mCollectionId;

    public DeleteCollectionOperation(final String pCollectionId) {
        mCollectionId = pCollectionId;
    }

    @Override
    public Void perform() throws Exception {
        final Selector selector = new Collection.ByIdSelector(mCollectionId);
        IDbTableConnector.Companion.getInstance().delete(Collection.DbKeys.TABLE_NAME, selector);
        IFirebaseDbConnector.Impl.Companion.getInstance().delete(Collection.DbKeys.TABLE_NAME, selector);
        return null;
    }
}
