package com.github.anastasiazhukova.flashlang.firebase.utils;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.github.anastasiazhukova.flashlang.Constants;

import java.io.ByteArrayOutputStream;

public class StorageUtils {

    @NonNull
    public static String getChildReference(final String pCollectionName, final String pPicName) {
        return String.format(Constants.FirebaseStorage.FULL_IMAGE_PATH_TEMPLATE, pCollectionName, pPicName);
    }

    public static byte[] toBytes(final Bitmap pBitmap) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pBitmap.compress(Constants.FirebaseStorage.DEFAULT_COMPRESS_FORMAT,
                Constants.FirebaseStorage.DEFAULT_COMPRESS_QUALITY, baos);
        return baos.toByteArray();
    }

}
