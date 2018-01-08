package com.github.anastasiazhukova.flashlang.domain.models.link;

import android.database.Cursor;
import android.provider.BaseColumns;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.firebase.db.annotations.FirebaseDbElement;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.lib.db.annotations.dbPrimaryKey;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbForeignKey;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.PropertyName;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static com.github.anastasiazhukova.flashlang.domain.models.link.CollectionCardLink.DbKeys.LINK_ID;
import static com.github.anastasiazhukova.flashlang.domain.models.link.UserCollectionLink.DbKeys.COLLECTION_ID;
import static com.github.anastasiazhukova.flashlang.domain.models.link.UserCollectionLink.DbKeys.TABLE_NAME;
import static com.github.anastasiazhukova.flashlang.domain.models.link.UserCollectionLink.DbKeys.USER_ID;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
@FirebaseDbElement(targetTableName = TABLE_NAME)
public class UserCollectionLink implements BaseColumns, IDbModel<String> {

    @dbPrimaryKey(isNull = false)
    @dbString(name = LINK_ID)
    private String mLinkId;

    @dbForeignKey(referredTableName = User.DbKeys.TABLE_NAME, referredTableColumnName = User.DbKeys.ID)
    @dbString(name = USER_ID)
    @PropertyName(USER_ID)
    private final String mUserId;

    @dbForeignKey(referredTableName = Collection.DbKeys.TABLE_NAME, referredTableColumnName = Collection.DbKeys.ID)
    @dbString(name = COLLECTION_ID)
    @PropertyName(COLLECTION_ID)
    private final String mCollectionId;

    UserCollectionLink(final String pLinkId, final String pUserId, final String pCollectionId) {
        mLinkId = pLinkId;
        mUserId = pUserId;
        mCollectionId = pCollectionId;
    }

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
    public String getId() {
        return mLinkId;
    }

    @Override
    public HashMap<String, Object> convertToInsert() {
        return convert();
    }

    @Override
    public HashMap<String, Object> convertToUpdate() {
        return convert();
    }

    private HashMap<String, Object> convert() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(USER_ID, mUserId);
        map.put(COLLECTION_ID, mCollectionId);
        return map;
    }

    public static final class DbKeys {

        static final String TABLE_NAME = "usercollection";
        static final String LINK_ID = "id";
        static final String USER_ID = "userId";
        static final String COLLECTION_ID = "collectionId";
    }

    public static final class CursorConverter implements ICursorConverter<UserCollectionLink> {

        @Override
        public UserCollectionLink convert(final Cursor pCursor) {
            final String linkID = pCursor.getColumnName(pCursor.getColumnIndex(LINK_ID));
            final String userId = pCursor.getString(pCursor.getColumnIndex(USER_ID));
            final String collectionId = pCursor.getString(pCursor.getColumnIndex(COLLECTION_ID));
            return new UserCollectionLink(linkID, userId, collectionId);
        }
    }

    public static final class DataSnapshotConverter implements IDataSnapshotConverter<UserCollectionLink> {

        @Override
        public UserCollectionLink convert(@NotNull final DataSnapshot pSnapshot) {
            final String linkId = (String) pSnapshot.child(LINK_ID).getValue();
            final String userId = (String) pSnapshot.child(USER_ID).getValue();
            final String collectionId = (String) pSnapshot.child(COLLECTION_ID).getValue();
            return new UserCollectionLink(linkId, userId, collectionId);
        }
    }

    public static class ByUserIdSelector extends Selector.ByFieldSelector {

        public ByUserIdSelector(final String pValue) {
            super(USER_ID, pValue);
        }
    }

    public static class ByCollectionIdSelector extends Selector.ByFieldSelector {

        public ByCollectionIdSelector(final String pValue) {
            super(COLLECTION_ID, pValue);
        }
    }

}
