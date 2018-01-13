package com.github.anastasiazhukova.flashlang.firebase.storage;

import android.graphics.Bitmap;
import android.net.Uri;
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
    public void upload(final String pCollectionName, final String pPicName, final Bitmap pBitmap, final ILoadListener pListener) {
        final byte[] data = StorageUtils.toBytes(pBitmap);
        final StorageReference imageReference = getImageReference(pCollectionName, pPicName);
        final UploadTask uploadTask = imageReference.putBytes(data);
        if (pListener != null) {
            uploadTask.addOnCompleteListener(new LoadOnCompleteListener(pListener));
        }

    }

    @Override
    public void upload(final String pCollectionName, final String pPicName, final InputStream pStream, final ILoadListener pListener) {
        final StorageReference imageReference = getImageReference(pCollectionName, pPicName);
        final UploadTask uploadTask = imageReference.putStream(pStream);
        if (pListener != null) {
            uploadTask.addOnCompleteListener(new LoadOnCompleteListener(pListener));
        }
    }

    @Override
    public Uri getImageUrl(final String pCollectionName, final String pPicName) {
        final StorageReference imageReference = getImageReference(pCollectionName, pPicName);
        final Task<Uri> downloadUrl = imageReference.getDownloadUrl();
        if (downloadUrl.isSuccessful()) {
            return downloadUrl.getResult();
        } else {
            return null;
        }
    }

    @NonNull
    private StorageReference getImageReference(final String pCollectionName, final String pPicName) {
        return mStorageReference.child(StorageUtils.getChildReference(pCollectionName, pPicName));
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
