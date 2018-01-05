package com.github.anastasiazhukova.flashlang.db.connector;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.lib.db.IDbOperations;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.utils.DbUtils;
import com.github.anastasiazhukova.lib.logs.Log;
import com.github.anastasiazhukova.lib.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;

public class DbTableConnector<Element extends IDbModel> implements IDbTableConnector<Element> {

    private static final String LOG_TAG = DbTableConnector.class.getSimpleName();

    private final IDbOperations mDbOperations;

    public DbTableConnector() {
        mDbOperations = IDbOperations.Impl.getInstance();
    }

    @Override
    public boolean insert(@dbTableElement final Element pElement) {
        final String tableName = getTableName(pElement);
        if (tableName != null) {
            try {
                final int inserted = mDbOperations.insert(tableName, convertToInsert(pElement));
                return inserted > 0;
            } catch (final Exception pE) {
                Log.e(LOG_TAG, pE.getMessage());
                throw pE;
            }
        }
        return false;
    }

    @Override
    public boolean insert(final String pTableName, final Element[] pElements) {
        final int inserted = mDbOperations.bulkInsert(pTableName, convertToInsert(pElements));
        return inserted == pElements.length;
    }

    @Override
    public boolean insert(@dbTableElement final Element[] pElements) {
        boolean isInserted = true;
        for (final Element element :
                pElements) {
            isInserted &= insert(element);
        }
        return isInserted;
    }

    @Override
    public boolean delete(final String pTableName, final ISelector pSelector) {
        final int deleted = mDbOperations.delete(pTableName, pSelector.getSelection(), null);
        return deleted > 0;
    }

    @Override
    public boolean update(@dbTableElement final Element pElement, final ISelector pSelector) {
        final String tableName = getTableName(pElement);
        if (tableName != null) {
            try {
                final int updated = mDbOperations.update(tableName, convertToUpdate(pElement), pSelector.getSelection(), null);
                return updated > 0;
            } catch (final Exception pE) {
                Log.e(LOG_TAG, pE.getMessage());
                throw pE;
            }
        }
        return false;
    }

    @Override
    public List<Element> get(final String pTableName, final ISelector pSelector, final ICursorConverter<Element> pCursorConverter) {
        Cursor cursor = null;
        try {
            cursor = mDbOperations.query()
                    .table(pTableName)
                    .selection(pSelector.getSelection())
                    .cursor();
            List<Element> elements = null;
            if (cursor != null) {
                elements = new ArrayList<>();
                while (cursor.moveToNext()) {
                    elements.add(pCursorConverter.convert(cursor));
                }
            }
            return elements;
        } catch (final Exception pE) {
            Log.e(LOG_TAG, pE.getMessage());
        } finally {
            IOUtils.close(cursor);
        }
        return null;
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

    private String getTableName(@dbTableElement final Element pElement) {
        return DbUtils.getTableName(pElement.getClass());
    }

}
