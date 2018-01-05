package com.github.anastasiazhukova.flashlang.dbTest;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.lib.db.annotations.dbPrimaryKey;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;

@dbTable(name = TestModel.TABLE_NAME)
@dbTableElement(targetTableName = TestModel.TABLE_NAME)
public class TestModel implements IDbModel {

    static final String ID = "id";
    public static final String STRING_KEY = "string";
    public static final String TABLE_NAME = "testTable";

    @dbString(name = ID)
    @dbPrimaryKey(isNull = false)
    private final String mId;

    @dbString(name = STRING_KEY)
    private final String mSomeString;

    TestModel(final String pId, final String pSomeString) {
        mId = pId;
        mSomeString = pSomeString;
    }

    public String getSomeString() {
        return mSomeString;
    }

    @Override
    public ContentValues convertToInsert() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(ID, mId);
        contentValues.put(STRING_KEY, mSomeString);
        return contentValues;
    }

    @Override
    public ContentValues convertToUpdate() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(STRING_KEY, mSomeString);
        return contentValues;
    }

    public static final class CursorConverter implements IDbTableConnector.ICursorConverter<TestModel> {

        @Override
        public TestModel convert(final Cursor pCursor) {
            final String mId = pCursor.getString(pCursor.getColumnIndex(ID));
            final String mString = pCursor.getString(pCursor.getColumnIndex(STRING_KEY));
            return new TestModel(mId, mString);
        }
    }

}
