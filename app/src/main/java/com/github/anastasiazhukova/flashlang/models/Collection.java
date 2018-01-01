package com.github.anastasiazhukova.flashlang.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.lib.db.annotations.dbPrimaryKey;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;

import static com.github.anastasiazhukova.flashlang.models.Collection.DbKeys.COVER;
import static com.github.anastasiazhukova.flashlang.models.Collection.DbKeys.ID;
import static com.github.anastasiazhukova.flashlang.models.Collection.DbKeys.NAME;
import static com.github.anastasiazhukova.flashlang.models.Collection.DbKeys.TABLE_NAME;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
public class Collection implements ICollection, IDbModel<ICollection> {

    @dbPrimaryKey(isNull = false)
    @dbString(name = ID)
    private final String mId;

    @dbString(name = NAME)
    private String mName;

    @dbString(name = COVER)
    private String mCoverUrl;

    public Collection(final String pId, final String pName, final String pCoverUrl) {
        mId = pId;
        mName = pName;
        mCoverUrl = pCoverUrl;
    }

    public void setName(final String pName) {
        mName = pName;
    }

    public void setCoverUrl(final String pCoverUrl) {
        mCoverUrl = pCoverUrl;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getCoverUrl() {
        return mCoverUrl;
    }

    @Override
    public ContentValues convertToInsert() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(ID, this.getId());
        contentValues.put(NAME, this.getName());
        contentValues.put(COVER, this.getCoverUrl());
        return contentValues;
    }

    @Override
    public ContentValues convertToUpdate() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, this.getName());
        contentValues.put(COVER, this.getCoverUrl());
        return contentValues;
    }

    public final class DbKeys {

        public static final String TABLE_NAME = "collections";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String COVER = "cover";
    }

    public static class CursorConverter implements IDbTableConnector.ICursorConverter<ICollection> {

        @Override
        public ICollection convert(final Cursor pCursor) {
            final String id = pCursor.getString(pCursor.getColumnIndex(ID));
            final String name = pCursor.getString(pCursor.getColumnIndex(NAME));
            final String picture = pCursor.getString(pCursor.getColumnIndex(COVER));

            return new Collection(id, name, picture);
        }
    }

    public static class ByIdSelector extends IDbTableConnector.ISelector.ByFieldSelector {

        public ByIdSelector(final String pFiledValue) {
            super(ID, pFiledValue);
        }
    }
}
