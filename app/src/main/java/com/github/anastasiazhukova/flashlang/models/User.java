package com.github.anastasiazhukova.flashlang.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.lib.db.annotations.dbPrimaryKey;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;

import static com.github.anastasiazhukova.flashlang.models.User.DbKeys.ID;
import static com.github.anastasiazhukova.flashlang.models.User.DbKeys.NAME;
import static com.github.anastasiazhukova.flashlang.models.User.DbKeys.PICTURE;
import static com.github.anastasiazhukova.flashlang.models.User.DbKeys.TABLE_NAME;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
public class User implements IUser, IDbModel {

    @dbPrimaryKey(isNull = false)
    @dbString(name = ID)
    private final String mId;

    @dbString(name = NAME)
    private String mName;

    @dbString(name = PICTURE)
    private String mPictureUrl;

    public User(final String pId, final String pName, final String pPictureUrl) {
        mId = pId;
        mName = pName;
        mPictureUrl = pPictureUrl;
    }

    public void setName(final String pName) {
        mName = pName;
    }

    public void setPictureUrl(final String pPictureUrl) {
        mPictureUrl = pPictureUrl;
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
    public String getPictureUrl() {
        return mPictureUrl;
    }

    @Override
    public ContentValues convertToInsert() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(ID, this.getId());
        contentValues.put(NAME, this.getName());
        contentValues.put(PICTURE, this.getPictureUrl());
        return contentValues;
    }

    @Override
    public ContentValues convertToUpdate() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, this.getName());
        contentValues.put(PICTURE, this.getPictureUrl());
        return contentValues;
    }

    public static final class DbKeys {

        public static final String TABLE_NAME = "users";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PICTURE = "picture";
    }

    public static class CursorConverter implements IDbTableConnector.ICursorConverter<IUser> {

        @Override
        public User convert(final Cursor pCursor) {
            final String id = pCursor.getString(pCursor.getColumnIndex(User.DbKeys.ID));
            final String name = pCursor.getString(pCursor.getColumnIndex(User.DbKeys.NAME));
            final String picture = pCursor.getString(pCursor.getColumnIndex(User.DbKeys.PICTURE));

            return new User(id, name, picture);
        }
    }

    public static class ByIdSelector extends IDbTableConnector.ISelector.ByFieldSelector {

        public ByIdSelector(final String pFiledValue) {
            super(ID, pFiledValue);
        }
    }

}
