package com.github.anastasiazhukova.flashlang.firebase.storage;

import android.graphics.Bitmap;
import android.net.Uri;

import com.github.anastasiazhukova.lib.contracts.ICallback;

import java.io.InputStream;

public interface IFirebaseStorageManager {

    void upload(String pCollectionName, String pPicName, Bitmap pBitmap, ILoadListener pListener);

    void upload(String pCollectionName, String pPicName, InputStream pStream, ILoadListener pListener);

    Uri getImageUrl(String pCollectionName, String pPicName);

    interface ILoadListener extends ICallback<Uri> {

    }

    final class Impl {

        public static IFirebaseStorageManager getInstance() {
            return new FirebaseStorageManager();
        }

    }

}
