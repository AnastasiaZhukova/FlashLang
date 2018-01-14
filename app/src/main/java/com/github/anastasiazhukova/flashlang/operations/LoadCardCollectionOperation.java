package com.github.anastasiazhukova.flashlang.operations;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.utils.OperationUtils;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class LoadCardCollectionOperation implements IOperation<Cursor> {

    private final String mUserId;
    private final String mSourceLanguageKey;
    private final String mTargetLanguageKey;

    public LoadCardCollectionOperation(final String pUserId, final String pSourceLanguageKey, final String pTargetLanguageKey) {
        mUserId = pUserId;
        mSourceLanguageKey = pSourceLanguageKey;
        mTargetLanguageKey = pTargetLanguageKey;
    }

    @Override
    public Cursor perform() throws Exception {
        final String collectionId = OperationUtils.getCollectionId(mUserId, mSourceLanguageKey, mTargetLanguageKey);
        if (collectionId != null) {
            final Cursor cursor = IDbTableConnector.Companion.getInstance().get(Card.DbKeys.TABLE_NAME, null,
                    new Collection.ByOwnerIdSelector(collectionId));
            return cursor;
        } else {
            throw new IllegalStateException("Can't find cards");
        }
    }
}
