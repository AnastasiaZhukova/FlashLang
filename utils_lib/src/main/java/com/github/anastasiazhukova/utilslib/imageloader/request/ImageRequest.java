package com.github.anastasiazhukova.utilslib.imageloader.request;

import android.widget.ImageView;

import com.github.anastasiazhukova.utilslib.imageloader.Louvre;

import java.lang.ref.WeakReference;

public class ImageRequest implements IImageRequest {

    private final String mUrl;
    private final WeakReference<ImageView> mTarget;
    private boolean isCached;
    private final Integer mErrorImage;

    public ImageRequest(final Builder pBuilder) {
        mUrl = pBuilder.mUrl;
        mTarget = pBuilder.mTarget;
        isCached = pBuilder.isCached;
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
    public boolean isCached() {
        return isCached;
    }

    @Override
    public Integer getErrorImage() {
        return mErrorImage;
    }

    public static class Builder {

        private String mUrl;
        private WeakReference<ImageView> mTarget;
        private boolean isCached = true;
        private Integer mErrorImage;

        public void from(final String pUrl) {
            mUrl = pUrl;
        }

        public void to(final ImageView pTarget) {
            mTarget = new WeakReference<>(pTarget);
        }

        public void cached(final boolean pCached) {
            isCached = pCached;
        }

        public void setErrorImage(final Integer pErrorImage) {
            mErrorImage = pErrorImage;
        }

        public IImageRequest build() {
            return new ImageRequest(this);
        }

        public void load() {
            Louvre.getInstance().load(build());
        }

    }

}
