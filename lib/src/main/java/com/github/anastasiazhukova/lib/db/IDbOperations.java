package com.github.anastasiazhukova.lib.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface IDbOperations {

    int insert(@NonNull String pTable, ContentValues pValues);

    int bulkInsert(@NonNull String pTable, ContentValues[] pValues);

    Cursor query(@NonNull String pTable, @Nullable final String[] pProjection, @Nullable final String pSelection, @Nullable final String[] pSelectionArgs, @Nullable final String pSortOrder);

    int delete(@NonNull String pTable, @Nullable final String pSelection, @Nullable final String[] pSelectionArgs);

    int update(@NonNull final String pTable, @Nullable final ContentValues pValues, @Nullable final String pSelection, @Nullable final String[] pSelectionArgs);

    final class Impl {

        public static IDbOperations newInstance(final Context pContext, final IDb pDb) {
            return new DbOperations(new DbHelper(pContext, pDb));
        }
    }

}
