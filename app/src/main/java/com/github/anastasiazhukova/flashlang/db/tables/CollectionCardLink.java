package com.github.anastasiazhukova.flashlang.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector.ISelector.ByFieldSelector;
import com.github.anastasiazhukova.flashlang.models.Card;
import com.github.anastasiazhukova.flashlang.models.Collection;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbForeignKey;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;

import static com.github.anastasiazhukova.flashlang.db.tables.CollectionCardLink.DbKeys.CARD_ID;
import static com.github.anastasiazhukova.flashlang.db.tables.CollectionCardLink.DbKeys.COLLECTION_ID;
import static com.github.anastasiazhukova.flashlang.db.tables.CollectionCardLink.DbKeys.TABLE_NAME;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
public class CollectionCardLink implements BaseColumns, IDbModel<CollectionCardLink> {

    @dbForeignKey(referredTableName = Collection.DbKeys.TABLE_NAME, referredTableColumnName = Collection.DbKeys.ID)
    @dbString(name = COLLECTION_ID)
    private final
    String mCollectionId;

    @dbForeignKey(referredTableName = Card.DbKeys.TABLE_NAME, referredTableColumnName = Card.DbKeys.ID)
    @dbString(name = CARD_ID)
    private final
    String mCardId;

    public CollectionCardLink(final String pCollectionId, final String pCardId) {
        mCollectionId = pCollectionId;
        mCardId = pCardId;
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
        contentValues.put(COLLECTION_ID, this.mCollectionId);
        contentValues.put(CARD_ID, this.mCardId);
        return contentValues;
    }

    public static final class DbKeys {

        static final String TABLE_NAME = "collectionname";
        static final String COLLECTION_ID = "collectionId";
        static final String CARD_ID = "cardId";
    }

    public static class CursorConverter implements IDbTableConnector.ICursorConverter<CollectionCardLink> {

        @Override
        public CollectionCardLink convert(final Cursor pCursor) {
            final String collectionId = pCursor.getString(pCursor.getColumnIndex(COLLECTION_ID));
            final String cardId = pCursor.getString(pCursor.getColumnIndex(CARD_ID));
            return new CollectionCardLink(collectionId, cardId);
        }
    }

    public static class ByCollectionIdSelector extends ByFieldSelector {

        public ByCollectionIdSelector(final String pValue) {
            super(COLLECTION_ID, pValue);
        }
    }

    public static class ByCardIdSelector extends ByFieldSelector {

        public ByCardIdSelector(String pValue) {
            super(CARD_ID, pValue);
        }
    }

}
