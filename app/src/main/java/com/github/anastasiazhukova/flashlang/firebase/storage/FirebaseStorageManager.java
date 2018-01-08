package com.github.anastasiazhukova.flashlang.firebase.storage;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.github.anastasiazhukova.flashlang.firebase.utils.StorageUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class FirebaseStorageManager implements IFirebaseStorageManager {

    private final StorageReference mStorageReference = FirebaseStorage.getInstance().getReference();

    @Override
    public void upload(final String pName, final Bitmap pBitmap, final ILoadListener pListener) {
        final byte[] data = StorageUtils.toBytes(pBitmap);
        final StorageReference imageReference = getImageReference(pName);
        imageReference.putBytes(data)
                .addOnCompleteListener(new LoadOnCompleteListener(pListener));
    }

    @Override
    public void upload(final String pName, final InputStream pStream, final ILoadListener pListener) {
        final StorageReference imageReference = getImageReference(pName);
        imageReference.putStream(pStream)
                .addOnCompleteListener(new LoadOnCompleteListener(pListener));
    }

    @NonNull
    private StorageReference getImageReference(final String pName) {
        return mStorageReference.child(StorageUtils.getChildReference(pName));
    }

    private class LoadOnCompleteListener implements OnCompleteListener<UploadTask.TaskSnapshot> {

        ILoadListener mLoadListener;

        public LoadOnCompleteListener(final ILoadListener pLoadListener) {
            mLoadListener = pLoadListener;
        }

        @Override
        public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> pTask) {
            if (!pTask.isSuccessful()) {
                mLoadListener.onError(pTask.getException());
            } else {
                mLoadListener.onSuccess(pTask.getResult().getDownloadUrl());
            }
        }
    }
}
