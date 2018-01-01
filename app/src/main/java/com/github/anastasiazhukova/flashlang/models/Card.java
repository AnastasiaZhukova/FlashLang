package com.github.anastasiazhukova.flashlang.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.lib.db.annotations.dbPrimaryKey;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;

import static com.github.anastasiazhukova.flashlang.models.Card.DbKeys.ID;
import static com.github.anastasiazhukova.flashlang.models.Card.DbKeys.SOURCE_LANGUAGE;
import static com.github.anastasiazhukova.flashlang.models.Card.DbKeys.SOURCE_TEXT;
import static com.github.anastasiazhukova.flashlang.models.Card.DbKeys.TABLE_NAME;
import static com.github.anastasiazhukova.flashlang.models.Card.DbKeys.TARGET_LANGUAGE;
import static com.github.anastasiazhukova.flashlang.models.Card.DbKeys.TRANSLATED_TEXT;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
public class Card implements ICard, IDbModel<ICard> {

    @dbPrimaryKey(isNull = false)
    @dbString(name = ID)
    private final String mId;

    @dbString(name = SOURCE_LANGUAGE)
    private final String mSourceLanguageKey;

    @dbString(name = SOURCE_TEXT)
    private final String mSourceText;

    @dbString(name = TARGET_LANGUAGE)
    private final String mTargetLanguage;

    @dbString(name = TRANSLATED_TEXT)
    private String mTranslatedText;

    public Card(final String pId, final String pSourceLanguageKey, final String pSourceText, final String pTargetLanguage, final String pTranslatedText) {
        mId = pId;
        mSourceLanguageKey = pSourceLanguageKey;
        mSourceText = pSourceText;
        mTargetLanguage = pTargetLanguage;
        mTranslatedText = pTranslatedText;
    }

    public void setTranslatedText(final String pTranslatedText) {
        mTranslatedText = pTranslatedText;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public String getSourceLanguageKey() {
        return mSourceLanguageKey;
    }

    @Override
    public String getSourceText() {
        return mSourceText;
    }

    @Override
    public String getTargetLanguageKey() {
        return mTargetLanguage;
    }

    @Override
    public String getTranslatedText() {
        return mTranslatedText;
    }

    @Override
    public ContentValues convertToInsert() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(ID, this.getId());
        contentValues.put(SOURCE_LANGUAGE, this.getSourceLanguageKey());
        contentValues.put(SOURCE_TEXT, this.getSourceText());
        contentValues.put(TARGET_LANGUAGE, this.getTargetLanguageKey());
        contentValues.put(TRANSLATED_TEXT, this.getTranslatedText());
        return contentValues;
    }

    @Override
    public ContentValues convertToUpdate() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(SOURCE_LANGUAGE, this.getSourceLanguageKey());
        contentValues.put(SOURCE_TEXT, this.getSourceText());
        contentValues.put(TARGET_LANGUAGE, this.getTargetLanguageKey());
        contentValues.put(TRANSLATED_TEXT, this.getTranslatedText());
        return contentValues;
    }

    public final class DbKeys {

        public static final String TABLE_NAME = "cards";
        public static final String ID = "id";
        public static final String SOURCE_LANGUAGE = "sourceLanguage";
        public static final String SOURCE_TEXT = "sourceText";
        public static final String TARGET_LANGUAGE = "targetLanguage";
        public static final String TRANSLATED_TEXT = "translatedText";
    }

    public static class CursorConverter implements IDbTableConnector.ICursorConverter<ICard> {

        @Override
        public ICard convert(final Cursor pCursor) {
            final String id = pCursor.getString(pCursor.getColumnIndex(ID));
            final String sl = pCursor.getString(pCursor.getColumnIndex(SOURCE_LANGUAGE));
            final String st = pCursor.getString(pCursor.getColumnIndex(SOURCE_TEXT));
            final String tl = pCursor.getString(pCursor.getColumnIndex(TARGET_LANGUAGE));
            final String tt = pCursor.getString(pCursor.getColumnIndex(TRANSLATED_TEXT));

            return new Card(id, sl, st, tl, tt);
        }
    }

    public static class ByIdSelector extends IDbTableConnector.ISelector.ByFieldSelector {

        public ByIdSelector(final String pFiledValue) {
            super(ID, pFiledValue);
        }
    }

    public static class BySourceLanguageSelector extends IDbTableConnector.ISelector.ByFieldSelector {

        public BySourceLanguageSelector(final String pFiledValue) {
            super(SOURCE_LANGUAGE, pFiledValue);
        }
    }

    public static class ByTargetLanguageSelector extends IDbTableConnector.ISelector.ByFieldSelector {

        public ByTargetLanguageSelector(final String pFiledValue) {
            super(TARGET_LANGUAGE, pFiledValue);
        }
    }

}
