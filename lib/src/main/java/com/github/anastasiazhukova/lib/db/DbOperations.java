package com.github.anastasiazhukova.lib.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class DbOperations implements IDbOperations {

    private final SQLiteOpenHelper mHelper;

    DbOperations(final SQLiteOpenHelper pHelper) {
        mHelper = pHelper;
    }

    @Override
    public int insert(@NonNull final String pTable, final ContentValues pValues) {
        final int id = makeTransaction(new ITransaction() {

            @Override
            public int make(final SQLiteDatabase pDatabase) {
                final int id = (int) pDatabase.insert(pTable, "", pValues);
                return id;
            }
        });
        return id;
    }

    @Override
    public int bulkInsert(@NonNull final String pTable, final ContentValues[] pValues) {
        final int inserted = makeTransaction(new ITransaction() {

            @Override
            public int make(final SQLiteDatabase pDatabase) {
                int inserted = 0;

                for (final ContentValues value : pValues) {
                    pDatabase.insert(pTable, "", value);
                    inserted++;
                }

                return inserted;
            }
        });
        return inserted;
    }

    @Override
    public Cursor query(@NonNull final String pTable, @Nullable final String[] pProjection, @Nullable final String pSelection, @Nullable final String[] pSelectionArgs, @Nullable final String pSortOrder) {
        return mHelper.getWritableDatabase().query(pTable, pProjection, pSelection, pSelectionArgs, null, null, pSortOrder);
    }

    @Override
    public int delete(@NonNull final String pTable, @Nullable final String pSelection, @Nullable final String[] pSelectionArgs) {
        final int count = makeTransaction(new ITransaction() {

            @Override
            public int make(final SQLiteDatabase pDatabase) {
                final int count = pDatabase.delete(pTable, pSelection, pSelectionArgs);
                return count;
            }
        });
        return count;
    }

    @Override
    public int update(@NonNull final String pTable, @Nullable final ContentValues pValues, @Nullable final String pSelection, @Nullable final String[] pSelectionArgs) {
        final int count = makeTransaction(new ITransaction() {

            @Override
            public int make(final SQLiteDatabase pDatabase) {
                final int count = pDatabase.update(pTable, pValues, pSelection, pSelectionArgs);
                return count;
            }
        });
        return count;
    }

    private int makeTransaction(final ITransaction pTransaction) {
        final SQLiteDatabase database = mHelper.getWritableDatabase();
        int count;

        database.beginTransaction();
        try {
            count = pTransaction.make(database);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return count;
    }

    private interface ITransaction {

        int make(SQLiteDatabase pDatabase);
    }
}
