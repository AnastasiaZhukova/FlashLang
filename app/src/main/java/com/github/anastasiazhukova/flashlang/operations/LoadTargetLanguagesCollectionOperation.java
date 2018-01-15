package com.github.anastasiazhukova.flashlang.operations;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class LoadTargetLanguagesCollectionOperation implements IOperation<Cursor> {

    private final String mUserId;
    private final String mSourceLanguageKeys;

    public LoadTargetLanguagesCollectionOperation(final String pUserId, final String pSourceLanguageKeys) {
        mUserId = pUserId;
        mSourceLanguageKeys = pSourceLanguageKeys;
    }

    @Override
    public Cursor perform() {
        final Cursor cursor = IDbTableConnector.Companion.getInstance()
                .get(Collection.DbKeys.TABLE_NAME, null, new Collection.ByOwnerIdSelector(mUserId),
                        new Collection.BySourceLanguageSelector(mSourceLanguageKeys));
        return cursor;
    }
}
