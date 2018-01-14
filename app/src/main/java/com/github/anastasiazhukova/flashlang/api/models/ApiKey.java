package com.github.anastasiazhukova.flashlang.api.models;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.lib.logs.Log;
import com.google.firebase.database.DataSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ApiKey implements IDbModel<String> {

    private final String mApiKey;

    public ApiKey(final String pApiKey) {
        mApiKey = pApiKey;
    }

    public String getApiKey() {
        return mApiKey;
    }

    @Override
    public Map<String, Object> convertToInsert() {
        throw new IllegalStateException("Can't insert new key from app");
    }

    @Override
    public Map<String, Object> convertToUpdate() {
        throw new IllegalStateException("Can't insert new key from app");
    }

    @Override
    public String getId() {
        return "apikey";
    }

    public static final class ApiKeyDatasnapshotConverter implements IDataSnapshotConverter<ApiKey> {

        @Override
        public ApiKey convert(@NotNull final DataSnapshot pSnapshot) {
            final String value = (String) pSnapshot.child("apikey").getValue();
            return new ApiKey(value);
        }
    }
}
