package com.github.anastasiazhukova.lib.imageloader.request;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.github.anastasiazhukova.lib.imageloader.ILouvre;

import java.lang.ref.WeakReference;

public class ImageRequest implements IImageRequest {

    private final String mUrl;
    private final WeakReference<ImageView> mTarget;
    private final boolean isSaved;
    private final boolean isRounded;
    private final Bitmap mErrorImage;

    ImageRequest(final Builder pBuilder) {
        mUrl = pBuilder.mUrl;
        mTarget = pBuilder.mTarget;
        isSaved = pBuilder.isSaved;
        isRounded = pBuilder.isRounded;
        mErrorImage = pBuilder.mErrorImage;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }

    @Override
    public WeakReference<ImageView> getTarget() {
        return mTarget;
    }

    @Override
    public boolean isSaved() {
        return isSaved;
    }

    @Override
    public boolean isRounded() {
        return isRounded;
    }

    @Override
    public Bitmap getErrorImage() {
        return mErrorImage;
    }

    public static class Builder {

        private String mUrl;
        private WeakReference<ImageView> mTarget;
        private boolean isSaved;
        private Bitmap mErrorImage;
        private boolean isRounded;

        public Builder from(final String pUrl) {
            mUrl = pUrl;
            return this;
        }

        public Builder to(final ImageView pTarget) {
            mTarget = new WeakReference<>(pTarget);
            return this;
        }

        public Builder saved(final boolean pSaved) {
            isSaved = pSaved;
            return this;
        }

        public Builder rounded(final boolean pRounded) {
            isRounded = pRounded;
            return this;
        }

        public Builder setErrorImage(final Bitmap pErrorImage) {
            mErrorImage = pErrorImage;
            return this;
        }

        IImageRequest build() {
            return new ImageRequest(this);
        }

        public void load() throws Exception {
            ILouvre.Impl.getInstance().handle(build());
        }

    }

}
