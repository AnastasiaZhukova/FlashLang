package com.github.anastasiazhukova.flashlang.domain.models.user;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.firebase.db.annotations.FirebaseDbElement;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.lib.db.annotations.dbPrimaryKey;
import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbInteger;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;
import com.github.anastasiazhukova.lib.utils.StringUtils;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.PropertyName;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static com.github.anastasiazhukova.flashlang.domain.models.user.User.DbKeys.ID;
import static com.github.anastasiazhukova.flashlang.domain.models.user.User.DbKeys.LANGUAGES_COUNT;
import static com.github.anastasiazhukova.flashlang.domain.models.user.User.DbKeys.NAME;
import static com.github.anastasiazhukova.flashlang.domain.models.user.User.DbKeys.PICTURE;
import static com.github.anastasiazhukova.flashlang.domain.models.user.User.DbKeys.TABLE_NAME;
import static com.github.anastasiazhukova.flashlang.domain.models.user.User.DbKeys.WORDS_COUNT;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
@FirebaseDbElement(targetTableName = TABLE_NAME)
public class User implements IUser, IDbModel<String> {

    @dbPrimaryKey(isNull = false)
    @dbString(name = ID)
    @PropertyName(ID)
    private final String mId;

    @dbString(name = NAME)
    @PropertyName(NAME)
    private String mName;

    @dbString(name = PICTURE)
    @PropertyName(PICTURE)
    private String mPictureUrl;

    @dbInteger(name = WORDS_COUNT)
    @PropertyName(WORDS_COUNT)
    private int mTotalWords;

    @dbInteger(name = LANGUAGES_COUNT)
    @PropertyName(LANGUAGES_COUNT)
    private int mTotalLanguages;

    public User(final UserInfo pUser) {
        mId = pUser.getUid();
        mName = pUser.getEmail();
        if (StringUtils.isNullOrEmpty(mName)) {
            mName = StringUtils.extractNameFromEmail(pUser.getEmail());
        }
        mPictureUrl = String.valueOf(pUser.getPhotoUrl());
    }

    public User(final UserBuilder pUserBuilder) {
        mId = pUserBuilder.getId();
        mName = pUserBuilder.getName();
        mPictureUrl = pUserBuilder.getPictureUrl();
        mTotalWords = pUserBuilder.getWordsCount();
        mTotalLanguages = pUserBuilder.getLanguagesCount();
    }

    public void setName(final String pName) {
        mName = pName;
    }

    public void setPictureUrl(final String pPictureUrl) {
        mPictureUrl = pPictureUrl;
    }

    public void increaseWordCount(final int pCount) {
        if (pCount < 0) {
            throw new IllegalStateException("Count is negative");
        }
        mTotalWords += pCount;
    }

    public void increaseLanguagesCount(final int pCount) {
        if (pCount < 0) {
            throw new IllegalStateException("Count is negative");
        }
        mTotalLanguages += pCount;
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
    public int getWordCount() {
        return mTotalWords;
    }

    @Override
    public int getLanguagesCount() {
        return mTotalLanguages;
    }

    @Override
    public HashMap<String, Object> convertToInsert() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(ID, this.getId());
        map.put(NAME, this.getName());
        map.put(PICTURE, this.getPictureUrl());
        map.put(WORDS_COUNT, this.getWordCount());
        map.put(LANGUAGES_COUNT, this.getLanguagesCount());
        return map;
    }

    @Override
    public HashMap<String, Object> convertToUpdate() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(NAME, this.getName());
        map.put(PICTURE, this.getPictureUrl());
        map.put(WORDS_COUNT, this.getWordCount());
        map.put(LANGUAGES_COUNT, this.getLanguagesCount());
        return map;
    }

    public static final class DbKeys {

        public static final String TABLE_NAME = "users";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PICTURE = "picture";
        public static final String WORDS_COUNT = "wordscount";
        public static final String LANGUAGES_COUNT = "languagescount";
    }

    public static class CursorConverter implements ICursorConverter<User> {

        @Override
        public User convert(@NonNull final Cursor pCursor) {
            final String id = pCursor.getString(pCursor.getColumnIndex(User.DbKeys.ID));
            final String name = pCursor.getString(pCursor.getColumnIndex(User.DbKeys.NAME));
            final String picture = pCursor.getString(pCursor.getColumnIndex(User.DbKeys.PICTURE));
            final int words = pCursor.getInt(pCursor.getColumnIndex(DbKeys.WORDS_COUNT));
            final int languages = pCursor.getInt(pCursor.getColumnIndex(DbKeys.LANGUAGES_COUNT));

            return new UserBuilder().setId(id)
                    .setName(name)
                    .setPictureUrl(picture)
                    .setWordsCount(words)
                    .setLanguagesCount(languages)
                    .createUser();
        }
    }

    public static class DataSnapshotConverter implements IDataSnapshotConverter<User> {

        @Override
        @Nullable
        public User convert(@NotNull final DataSnapshot pSnapshot) {
            final String id = (String) pSnapshot.child(ID).getValue();
            final String name = (String) pSnapshot.child(NAME).getValue();
            final String pic = (String) pSnapshot.child(PICTURE).getValue();
            final int words = (int) pSnapshot.child(WORDS_COUNT).getValue();
            final int languages = (int) pSnapshot.child(LANGUAGES_COUNT).getValue();
            return new UserBuilder().setId(id)
                    .setName(name)
                    .setPictureUrl(pic)
                    .setWordsCount(words)
                    .setLanguagesCount(languages)
                    .createUser();
        }
    }

    public static class ByIdSelector extends Selector.ByFieldSelector {

        public ByIdSelector(final String pFiledValue) {
            super(ID, pFiledValue);
        }
    }

    public static class ByNameSelector extends Selector.ByFieldSelector {

        public ByNameSelector(@NotNull final String fieldValue) {
            super(NAME, fieldValue);
        }
    }

}
