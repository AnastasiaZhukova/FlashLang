package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.flashlang.utils.OperationUtils;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class CreateCollectionOperation implements IOperation<String> {

    private final String mUserId;
    private final String mSourceLanguageKey;
    private final String mTargetLanguageKey;

    public CreateCollectionOperation(final String pUserId, final String pSourceLanguageKey, final String pTargetLanguageKey) {
        mUserId = pUserId;
        mSourceLanguageKey = pSourceLanguageKey;
        mTargetLanguageKey = pTargetLanguageKey;
    }

    @Override
    public String perform() throws Exception {
        final String id = OperationUtils.getIdForCollection();
        final Collection collection = new Collection(id, mUserId,
                mSourceLanguageKey, mTargetLanguageKey);
        IDbTableConnector.Companion.getInstance().insert(collection);
        IFirebaseDbConnector.Impl.Companion.getInstance().insert(collection);
        return id;
    }

}
