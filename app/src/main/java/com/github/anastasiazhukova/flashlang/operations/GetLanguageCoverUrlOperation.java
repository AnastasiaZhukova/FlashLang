package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.Constants;
import com.github.anastasiazhukova.flashlang.firebase.storage.IFirebaseStorageManager;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class GetLanguageCoverUrlOperation implements IOperation<Void> {

    private final String mLanguageKey;
    private final IFirebaseStorageManager.ILoadListener mListener;

    public GetLanguageCoverUrlOperation(final String pLanguageKey, final IFirebaseStorageManager.ILoadListener pListener) {
        mLanguageKey = pLanguageKey;
        mListener = pListener;
    }

    @Override
    public Void perform() throws Exception {
        IFirebaseStorageManager.Impl.getInstance()
                .getImageUrl(Constants.FirebaseStorage.LANGUAGES_IMAGE_FOLDER_NAME, mLanguageKey, mListener);
        return null;
    }
}
