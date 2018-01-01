package com.github.anastasiazhukova.flashlang.db.connector;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.lib.db.IDbOperations;

import java.util.ArrayList;
import java.util.List;

public class DbTableConnector<Element extends IDbModel> implements IDbTableConnector<Element> {

    private final IDbOperations mDbOperations;
    private final String mTableName;

    public DbTableConnector(final String pTableName) {
        mDbOperations = IDbOperations.Impl.getInstance();
        mTableName = pTableName;
    }

    @Override
    public boolean insert(final Element pElement) {
        final int inserted = mDbOperations.insert(mTableName, convertToInsert(pElement));
        return inserted > 0;
    }

    @Override
    public boolean insert(final Element[] pElements) {
        final int inserted = mDbOperations.bulkInsert(mTableName, convertToInsert(pElements));
        return inserted == pElements.length;
    }

    @Override
    public boolean delete(final ISelector pSelector) {
        final int deleted = mDbOperations.delete(mTableName, pSelector.getSelection(), null);
        return deleted > 0;
    }

    @Override
    public boolean update(final Element pElement, final ISelector pSelector) {
        final int updated = mDbOperations.update(mTableName, convertToUpdate(pElement), pSelector.getSelection(), null);
        return updated > 0;
    }

    @Override
    public List<Element> get(final ISelector pSelector, final ICursorConverter<Element> pCursorConverter) {
        final Cursor cursor = mDbOperations.query(mTableName, null, pSelector.getSelection(), null, null);
        List<Element> elements = null;
        if (cursor != null) {
            elements = new ArrayList<>();
            while (cursor.moveToNext()) {
                elements.add(pCursorConverter.convert(cursor));
            }
            cursor.close();
        }
        return elements;
    }

    private ContentValues convertToInsert(final Element pElement) {
        return pElement.convertToInsert();
    }

    private ContentValues[] convertToInsert(final Element[] pElements) {
        final ContentValues[] contentValues = new ContentValues[pElements.length];
        for (int i = 0; i < pElements.length; i++) {
            contentValues[i] = convertToInsert(pElements[i]);
        }
        return contentValues;
    }

    private ContentValues convertToUpdate(final Element pElement) {
        return pElement.convertToUpdate();
    }

}
