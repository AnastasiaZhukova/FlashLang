package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.Constants;
import com.github.anastasiazhukova.flashlang.api.models.ApiKey;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IGetCallback;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;

import java.util.List;

public class GetTranslateApiKeyOperation implements IOperation<Void> {

    private static final String LOG_TAG = GetTranslateApiKeyOperation.class.getSimpleName();

    private final ICallback<String> mCallback;

    public GetTranslateApiKeyOperation(final ICallback<String> pCallback) {
        mCallback = pCallback;
    }

    @Override
    public Void perform() {
        //noinspection unchecked
        IFirebaseDbConnector.Impl.Companion.getInstance()
                .query()
                .tableName(Constants.FirebaseDb.API_KEY_REF)
                .converter(new ApiKey.ApiKeyDatasnapshotConverter())
                .get(new IGetCallback<ApiKey>() {

                    @Override
                    public void onSuccess(final List<? extends ApiKey> pApiKeys) {
                        if (pApiKeys != null && !pApiKeys.isEmpty()) {
                            final ApiKey apiKey = pApiKeys.get(0);
                            if (apiKey != null) {
                                mCallback.onSuccess(apiKey.getApiKey());
                                return;
                            }
                            mCallback.onError(new IllegalStateException("Can't get api key"));
                        }
                    }

                    @Override
                    public void onError(final Throwable pThrowable) {
                        mCallback.onError(pThrowable);
                    }
                });
        return null;
    }

}
