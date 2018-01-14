package com.github.anastasiazhukova.flashlang.operations;

import android.net.Uri;

import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.domain.models.collection.CollectionBuilder;
import com.github.anastasiazhukova.flashlang.firebase.storage.IFirebaseStorageManager;
import com.github.anastasiazhukova.flashlang.utils.OperationUtils;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;

public class CreateCollectionOperation implements IOperation<String> {

    private final String mUserId;
    private final String mSourceLanguageKey;
    private final String mTargetLanguageKey;
    private Collection mCollection;

    public CreateCollectionOperation(final String pUserId, final String pSourceLanguageKey, final String pTargetLanguageKey) {
        mUserId = pUserId;
        mSourceLanguageKey = pSourceLanguageKey;
        mTargetLanguageKey = pTargetLanguageKey;
    }

    @Override
    public String perform() throws Exception {
        final String id = OperationUtils.getIdForCollection();
        final CollectionBuilder collectionBuilder = new CollectionBuilder().setId(id)
                .setOwnerId(mUserId)
                .setSourceLanguage(mSourceLanguageKey)
                .setTargetLanguage(mTargetLanguageKey);

        getLanguageCoverUrl(mSourceLanguageKey, new ICallback<String>() {

            @Override
            public void onSuccess(final String pS) {
                collectionBuilder.setSourceLanguageCover(pS);
                loadNext();
            }

            @Override
            public void onError(final Throwable pThrowable) {
                loadNext();
            }

            private void loadNext() {
                getLanguageCoverUrl(mTargetLanguageKey, new ICallback<String>() {

                    @Override
                    public void onSuccess(final String pS) {
                        collectionBuilder.setTargetLanguageCover(pS);
                        performNext();
                    }

                    @Override
                    public void onError(final Throwable pThrowable) {
                        performNext();
                    }

                    private void performNext() {
                        putToDb(collectionBuilder.createCollection());
                    }
                });
            }
        });

        return id;
    }

    private void getLanguageCoverUrl(final String pLanguageKey, final ICallback<String> pCallback) {
        final IOperation operation = new GetLanguageCoverUrlOperation(pLanguageKey, new IFirebaseStorageManager.ILoadListener() {

            @Override
            public void onSuccess(final Uri pUri) {
                pCallback.onSuccess(pUri.toString());
            }

            @Override
            public void onError(final Throwable pThrowable) {
                pCallback.onError(pThrowable);
            }
        });
        ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD)
                .execute(operation);
    }

    private void putToDb(final Collection mCollection) {
        UploadCollectionToDbOperation operation = new UploadCollectionToDbOperation(mCollection);
        ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD)
                .execute(operation);
    }
}
