package com.github.anastasiazhukova.utilslib.imageloader.utils;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.github.anastasiazhukova.utilslib.imageloader.request.IImageRequest;

public final class ImageUtils {

    public static boolean imageHasSize(final IImageRequest pImageRequest) {

        if (pImageRequest.getWidth() > 0 && pImageRequest.getHeight() > 0) {
            return true;
        }

        final ImageView imageView = pImageRequest.getTarget().get();
        final int width;
        final int height;
        if (imageView != null && (width = imageView.getWidth()) > 0 && (height = imageView.getHeight()) > 0) {
            pImageRequest.setSize(width, height);
            return true;
        }
        return false;
    }

    public static void setImageResource(@NonNull final ImageView pTarget, final int pImageResource) {
        pTarget.setImageResource(pImageResource);
    }
}
