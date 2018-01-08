package com.github.anastasiazhukova.flashlang.firebase.utils;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.github.anastasiazhukova.flashlang.Constants;

import java.io.ByteArrayOutputStream;

public class StorageUtils {

    @NonNull
    public static String getChildReference(final String pName) {
        return String.format(Constants.FirebaseStorage.FULL_IMAGE_PATH_TEMPLATE, pName);
    }

    public static byte[] toBytes(final Bitmap pBitmap) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pBitmap.compress(Constants.FirebaseStorage.DEFAULT_COMPRESS_FORMAT,
                Constants.FirebaseStorage.DEFAULT_COMPRESS_QUALITY, baos);
        return baos.toByteArray();
    }

}
