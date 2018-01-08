package com.github.anastasiazhukova.flashlang.domain.models.card;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.firebase.db.annotations.FirebaseDbElement;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.lib.db.annotations.dbPrimaryKey;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.PropertyName;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static com.github.anastasiazhukova.flashlang.domain.models.card.Card.DbKeys.ID;
import static com.github.anastasiazhukova.flashlang.domain.models.card.Card.DbKeys.SOURCE_LANGUAGE;
import static com.github.anastasiazhukova.flashlang.domain.models.card.Card.DbKeys.SOURCE_TEXT;
import static com.github.anastasiazhukova.flashlang.domain.models.card.Card.DbKeys.TABLE_NAME;
import static com.github.anastasiazhukova.flashlang.domain.models.card.Card.DbKeys.TARGET_LANGUAGE;
import static com.github.anastasiazhukova.flashlang.domain.models.card.Card.DbKeys.TRANSLATED_TEXT;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
@FirebaseDbElement(targetTableName = TABLE_NAME)
public class Card implements ICard, IDbModel<String> {

    @dbPrimaryKey(isNull = false)
    @dbString(name = ID)
    @PropertyName(ID)
    private String mId;

    @dbString(name = SOURCE_LANGUAGE)
    @PropertyName(SOURCE_LANGUAGE)
    private String mSourceLanguageKey;

    @dbString(name = SOURCE_TEXT)
    @PropertyName(SOURCE_TEXT)
    private String mSourceText;

    @dbString(name = TARGET_LANGUAGE)
    @PropertyName(TARGET_LANGUAGE)
    private String mTargetLanguage;

    @dbString(name = TRANSLATED_TEXT)
    @PropertyName(TRANSLATED_TEXT)
    private String mTranslatedText;

    public Card() {
    }

    Card(final String pId, final String pSourceLanguageKey, final String pSourceText, final String pTargetLanguage, final String pTranslatedText) {
        mId = pId;
        mSourceLanguageKey = pSourceLanguageKey;
        mSourceText = pSourceText;
        mTargetLanguage = pTargetLanguage;
        mTranslatedText = pTranslatedText;
    }

    public Card(final CardBuilder pCardBuilder) {
        mId = pCardBuilder.getId();
        mSourceLanguageKey = getSourceLanguageKey();
        mSourceText = getSourceText();
        mTargetLanguage = getTargetLanguageKey();
        mTranslatedText = getTranslatedText();
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
    public HashMap<String, Object> convertToInsert() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(ID, this.getId());
        map.put(SOURCE_LANGUAGE, this.getSourceLanguageKey());
        map.put(SOURCE_TEXT, this.getSourceText());
        map.put(TARGET_LANGUAGE, this.getTargetLanguageKey());
        map.put(TRANSLATED_TEXT, this.getTranslatedText());
        return map;
    }

    @Override
    public HashMap<String, Object> convertToUpdate() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(SOURCE_LANGUAGE, this.getSourceLanguageKey());
        map.put(SOURCE_TEXT, this.getSourceText());
        map.put(TARGET_LANGUAGE, this.getTargetLanguageKey());
        map.put(TRANSLATED_TEXT, this.getTranslatedText());
        return map;
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
        public ICard convert(@NonNull final Cursor pCursor) {
            final String id = pCursor.getString(pCursor.getColumnIndex(ID));
            final String sl = pCursor.getString(pCursor.getColumnIndex(SOURCE_LANGUAGE));
            final String st = pCursor.getString(pCursor.getColumnIndex(SOURCE_TEXT));
            final String tl = pCursor.getString(pCursor.getColumnIndex(TARGET_LANGUAGE));
            final String tt = pCursor.getString(pCursor.getColumnIndex(TRANSLATED_TEXT));

            return new Card(id, sl, st, tl, tt);
        }
    }

    public static class DataSnapshotConverter implements IDataSnapshotConverter<Card> {

        @Override
        public Card convert(@NotNull final DataSnapshot pSnapshot) {
            Card card = pSnapshot.getValue(Card.class);
            return card;
        }
    }

    public static class ByIdSelector extends Selector.ByFieldSelector {

        public ByIdSelector(final String pFiledValue) {
            super(ID, pFiledValue);
        }
    }

    public static class BySourceLanguageSelector extends Selector.ByFieldSelector {

        public BySourceLanguageSelector(final String pFiledValue) {
            super(SOURCE_LANGUAGE, pFiledValue);
        }
    }

    public static class ByTargetLanguageSelector extends Selector.ByFieldSelector {

        public ByTargetLanguageSelector(final String pFiledValue) {
            super(TARGET_LANGUAGE, pFiledValue);
        }
    }

}
