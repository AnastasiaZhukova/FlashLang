package com.github.anastasiazhukova.flashlang.operations;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class LoadSourceLanguagesCollectionOperation implements IOperation<Cursor> {

    private final String mUserId;

    public LoadSourceLanguagesCollectionOperation(final String pUserId) {
        mUserId = pUserId;
    }

    @Override
    public Cursor perform() throws Exception {
        final Cursor cursor = IDbTableConnector.Companion.getInstance()
                .get(Collection.DbKeys.TABLE_NAME, Collection.DbKeys.SOURCE_LANGUAGE,
                        new Collection.ByOwnerIdSelector(mUserId));
        return cursor;
    }
}
