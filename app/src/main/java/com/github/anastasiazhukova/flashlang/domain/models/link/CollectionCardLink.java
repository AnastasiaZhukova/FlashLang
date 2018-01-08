package com.github.anastasiazhukova.flashlang.domain.models.link;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
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

import static com.github.anastasiazhukova.flashlang.domain.models.link.CollectionCardLink.DbKeys.CARD_ID;
import static com.github.anastasiazhukova.flashlang.domain.models.link.CollectionCardLink.DbKeys.COLLECTION_ID;
import static com.github.anastasiazhukova.flashlang.domain.models.link.CollectionCardLink.DbKeys.LINK_ID;
import static com.github.anastasiazhukova.flashlang.domain.models.link.CollectionCardLink.DbKeys.TABLE_NAME;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
@FirebaseDbElement(targetTableName = TABLE_NAME)
public class CollectionCardLink implements IDbModel<String> {

    @dbPrimaryKey(isNull = false)
    @dbString(name = LINK_ID)
    private String mLinkId;

    @dbForeignKey(referredTableName = Collection.DbKeys.TABLE_NAME, referredTableColumnName = Collection.DbKeys.ID)
    @dbString(name = COLLECTION_ID)
    @PropertyName(COLLECTION_ID)
    private String mCollectionId;

    @dbForeignKey(referredTableName = Card.DbKeys.TABLE_NAME, referredTableColumnName = Card.DbKeys.ID)
    @dbString(name = CARD_ID)
    @PropertyName(CARD_ID)
    private String mCardId;

    public CollectionCardLink() {
    }

    public CollectionCardLink(final String pCollectionId, final String pCardId) {
        mCollectionId = pCollectionId;
        mCardId = pCardId;
    }

    @Override
    public String getId() {
        return mLinkId;
    }

    public String getCollectionId() {
        return mCollectionId;
    }

    public String getCardId() {
        return mCardId;
    }

    @Override
    public HashMap<String, Object> convertToInsert() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(LINK_ID, this.mLinkId);
        map.put(COLLECTION_ID, this.mCollectionId);
        map.put(CARD_ID, this.mCardId);
        return map;
    }

    @Override
    public HashMap<String, Object> convertToUpdate() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(COLLECTION_ID, this.mCollectionId);
        map.put(CARD_ID, this.mCardId);
        return map;
    }

    public static final class DbKeys {

        static final String TABLE_NAME = "collectionname";
        static final String LINK_ID = "id";
        static final String COLLECTION_ID = "collectionId";
        static final String CARD_ID = "cardId";
    }

    public static class CursorConverter implements IDbTableConnector.ICursorConverter<CollectionCardLink> {

        @Override
        public CollectionCardLink convert(@NonNull final Cursor pCursor) {
            final String collectionId = pCursor.getString(pCursor.getColumnIndex(COLLECTION_ID));
            final String cardId = pCursor.getString(pCursor.getColumnIndex(CARD_ID));
            return new CollectionCardLink(collectionId, cardId);
        }
    }

    public static class DataSnapshotConverter implements IDataSnapshotConverter<CollectionCardLink> {

        @Override
        public CollectionCardLink convert(@NotNull final DataSnapshot pSnapshot) {
            CollectionCardLink link = pSnapshot.getValue(CollectionCardLink.class);
            return link;
        }
    }

    public static class ByCollectionIdSelector extends Selector.ByFieldSelector {

        public ByCollectionIdSelector(final String pValue) {
            super(COLLECTION_ID, pValue);
        }
    }

    public static class ByCardIdSelector extends Selector.ByFieldSelector {

        public ByCardIdSelector(final String pValue) {
            super(CARD_ID, pValue);
        }
    }

}
