package com.github.anastasiazhukova.lib.imageloader.utils;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.github.anastasiazhukova.lib.imageloader.request.IImageRequest;

public final class ImageUtils {

    public static boolean imageHasSize(final IImageRequest pImageRequest) {

        if (pImageRequest.getWidth() > 0 && pImageRequest.getHeight() > 0) {
            return true;
        }

        final ImageView imageView = pImageRequest.getTarget().get();
        final int width;
        final int height;
        if (imageView != null && (width = imageView.getWidth()) > 0 && (height = imageView.getHeight()) > 0) {
            setSize(pImageRequest, width, height);
            return true;
        }
        return false;
    }

    public static void setSize(final IImageRequest pImageRequest, final int pWidth, final int pHeight) {
        if (pImageRequest != null) {
            pImageRequest.setWidth(pWidth);
            pImageRequest.setHeight(pHeight);
        }
    }

    public static void setImage(@NonNull final ImageView pTarget, final Integer pImage) {
        if (pImage != null) {
            pTarget.setImageResource(pImage);
        }
    }

    public static void setImage(@NonNull final ImageView pTarget, final Bitmap pBitmap) {
        if (pBitmap != null) {
            pTarget.setImageBitmap(pBitmap);
        }
    }
}
