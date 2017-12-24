package com.github.anastasiazhukova.lib.imageloader.request;

import android.widget.ImageView;

import com.github.anastasiazhukova.lib.imageloader.Louvre;

import java.lang.ref.WeakReference;

public class ImageRequest implements IImageRequest {

    private final String mUrl;
    private final WeakReference<ImageView> mTarget;
    private final boolean isSaved;
    private final Integer mErrorImage;

    private int mWidth;
    private int mHeight;

    ImageRequest(final Builder pBuilder) {
        mUrl = pBuilder.mUrl;
        mTarget = pBuilder.mTarget;
        isSaved = pBuilder.isSaved;
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
    public int getWidth() {
        return mWidth;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public void setWidth(final int pWidth) {
        mWidth = pWidth;
    }

    @Override
    public void setHeight(final int pHeight) {
        mHeight = pHeight;
    }

    @Override
    public boolean isSaved() {
        return isSaved;
    }

    @Override
    public Integer getErrorImage() {
        return mErrorImage;
    }

    public static class Builder {

        private String mUrl;
        private WeakReference<ImageView> mTarget;
        private boolean isSaved;
        private Integer mErrorImage;

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

        public Builder setErrorImage(final Integer pErrorImage) {
            mErrorImage = pErrorImage;
            return this;
        }

        public IImageRequest build() {
            return new ImageRequest(this);
        }

        public void load() throws Exception {
            Louvre.getInstance().handle(build());
        }

    }

}
