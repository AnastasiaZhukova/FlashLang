package com.github.anastasiazhukova.flashlang.firebase.storage;

import android.graphics.Bitmap;
import android.net.Uri;

import com.github.anastasiazhukova.lib.contracts.ICallback;

import java.io.InputStream;

public interface IFirebaseStorageManager {

    void upload(String pName, Bitmap pBitmap, ILoadListener pListener);

    void upload(String pName, InputStream pStream, ILoadListener pListener);

    interface ILoadListener extends ICallback<Uri> {

    }

    final class Impl {

        public static IFirebaseStorageManager getInstence() {
            return new FirebaseStorageManager();
        }

    }

}
