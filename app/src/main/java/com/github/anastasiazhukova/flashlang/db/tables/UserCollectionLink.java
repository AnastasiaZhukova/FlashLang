package com.github.anastasiazhukova.flashlang.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.models.Collection;
import com.github.anastasiazhukova.flashlang.models.User;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbForeignKey;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;

import static com.github.anastasiazhukova.flashlang.db.tables.UserCollectionLink.DbKeys.COLLECTION_ID;
import static com.github.anastasiazhukova.flashlang.db.tables.UserCollectionLink.DbKeys.TABLE_NAME;
import static com.github.anastasiazhukova.flashlang.db.tables.UserCollectionLink.DbKeys.USER_ID;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
public class UserCollectionLink implements BaseColumns, IDbModel<UserCollectionLink> {

    @dbForeignKey(referredTableName = User.DbKeys.TABLE_NAME, referredTableColumnName = User.DbKeys.ID)
    @dbString(name = USER_ID)
    private final
    String mUserId;

    @dbForeignKey(referredTableName = Collection.DbKeys.TABLE_NAME, referredTableColumnName = Collection.DbKeys.ID)
    @dbString(name = COLLECTION_ID)
    private final
    String mCollectionId;

    public UserCollectionLink(final String pUserId, final String pCollectionId) {
        mUserId = pUserId;
        mCollectionId = pCollectionId;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getCollectionId() {
        return mCollectionId;
    }

    @Override
    public ContentValues convertToInsert() {
        return convert();
    }

    @Override
    public ContentValues convertToUpdate() {
        return convert();
    }

    private ContentValues convert() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, mUserId);
        contentValues.put(COLLECTION_ID, mCollectionId);
        return contentValues;
    }

    public static final class DbKeys {

        static final String TABLE_NAME = "usercollection";
        static final String USER_ID = "userId";
        static final String COLLECTION_ID = "collectionId";
    }

    public static final class CursorConverter implements IDbTableConnector.ICursorConverter<UserCollectionLink> {

        @Override
        public UserCollectionLink convert(final Cursor pCursor) {
            final String userId = pCursor.getString(pCursor.getColumnIndex(USER_ID));
            final String collectionId = pCursor.getString(pCursor.getColumnIndex(COLLECTION_ID));
            return new UserCollectionLink(userId, collectionId);
        }
    }

    public static class ByUserIdSelector extends IDbTableConnector.ISelector.ByFieldSelector {

        public ByUserIdSelector(final String pValue) {
            super(USER_ID, pValue);
        }
    }

    public static class ByCollectionIdSelector extends IDbTableConnector.ISelector.ByFieldSelector {

        public ByCollectionIdSelector(final String pValue) {
            super(COLLECTION_ID, pValue);
        }
    }

}
