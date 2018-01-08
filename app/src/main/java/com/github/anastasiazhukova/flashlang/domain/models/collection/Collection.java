package com.github.anastasiazhukova.flashlang.domain.models.collection;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
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

import static com.github.anastasiazhukova.flashlang.domain.models.collection.Collection.DbKeys.COVER;
import static com.github.anastasiazhukova.flashlang.domain.models.collection.Collection.DbKeys.ID;
import static com.github.anastasiazhukova.flashlang.domain.models.collection.Collection.DbKeys.NAME;
import static com.github.anastasiazhukova.flashlang.domain.models.collection.Collection.DbKeys.TABLE_NAME;

@dbTable(name = TABLE_NAME)
@dbTableElement(targetTableName = TABLE_NAME)
@FirebaseDbElement(targetTableName = TABLE_NAME)
public class Collection implements ICollection, IDbModel<String> {

    @dbPrimaryKey(isNull = false)
    @dbString(name = ID)
    @PropertyName(ID)
    private String mId;

    @dbString(name = NAME)
    @PropertyName(NAME)
    private String mName;

    @dbString(name = COVER)
    @PropertyName(COVER)
    private String mCoverUrl;

    public Collection() {
    }

    Collection(final String pId, final String pName, final String pCoverUrl) {
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
    public HashMap<String, Object> convertToInsert() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(ID, this.getId());
        map.put(NAME, this.getName());
        map.put(COVER, this.getCoverUrl());
        return map;
    }

    @Override
    public HashMap<String, Object> convertToUpdate() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put(NAME, this.getName());
        map.put(COVER, this.getCoverUrl());
        return map;
    }

    public final class DbKeys {

        public static final String TABLE_NAME = "collections";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String COVER = "cover";
    }

    public static class CursorConverter implements ICursorConverter<ICollection> {

        @Override
        public ICollection convert(@NonNull final Cursor pCursor) {
            final String id = pCursor.getString(pCursor.getColumnIndex(ID));
            final String name = pCursor.getString(pCursor.getColumnIndex(NAME));
            final String picture = pCursor.getString(pCursor.getColumnIndex(COVER));

            return new Collection(id, name, picture);
        }
    }

    public static class DataShanpshotConverter implements IDataSnapshotConverter<ICollection> {

        @Override
        public Collection convert(@NotNull final DataSnapshot pSnapshot) {
            final String id = (String) pSnapshot.child(ID).getValue();
            final String name = (String) pSnapshot.child(NAME).getValue();
            final String cover = (String) pSnapshot.child(COVER).getValue();
            return new Collection(id, name, cover);
        }
    }

    public static class ByIdSelector extends Selector.ByFieldSelector {

        public ByIdSelector(final String pFiledValue) {
            super(ID, pFiledValue);
        }
    }

}
