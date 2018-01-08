package com.github.anastasiazhukova.flashlang.firebase.db;

import android.support.annotation.Nullable;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IGetCallback;
import com.google.firebase.database.Query;

public class FirebaseQuery {

    private String mTableName;
    private Selector mSelector;
    private String mStartAt;
    private int limit;

    @Nullable
    public String getTableName() {
        return mTableName;
    }

    @Nullable
    public Selector getSelector() {
        return mSelector;
    }

    @Nullable
    public String getStartAt() {
        return mStartAt;
    }

    @Nullable
    public int getLimit() {
        return limit;
    }

    public FirebaseQuery tableName(final String pTableName) {
        mTableName = pTableName;
        return this;
    }

    public FirebaseQuery selector(final Selector pSelector) {
        mSelector = pSelector;
        return this;
    }

    public FirebaseQuery startAt(final String pStartAt) {
        mStartAt = pStartAt;
        return this;
    }

    public FirebaseQuery limit(final int pLimit) {
        limit = pLimit;
        return this;
    }

    public <Element extends IDbModel<String>> void get(final IDataSnapshotConverter<Element> pConverter,
                                                       final IGetCallback<Element> pCallback) {
        IFirebaseDbConnector.Impl.Companion.getInstance().get(this, pConverter, pCallback);
    }

    public Query query() {
        Query query = IFirebaseDbConnector.Impl.Companion.getInstance().query(this);
        return query;
    }
}
