package com.github.anastasiazhukova.flashlang.operations.impl.info.storage;

import android.graphics.drawable.Drawable;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.firebase.storage.IFirebaseStorageManager;
import com.github.anastasiazhukova.flashlang.operations.impl.info.storage.impl.GetLanguageCoverUrlOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.storage.impl.UploadUserImageOperation;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class StorageOperationsBuilder {

    public IOperation<Void> getLanguageCoverUrl(final String pLanguageKey, final IFirebaseStorageManager.ILoadListener pListener) {
        return new GetLanguageCoverUrlOperation(pLanguageKey, pListener);
    }

    public IOperation<Void> uploadUserImage(final User pUser, final Drawable pDrawable, final ICallback<Void> pCallback) {
        return new UploadUserImageOperation(pUser, pDrawable, pCallback);
    }

}
