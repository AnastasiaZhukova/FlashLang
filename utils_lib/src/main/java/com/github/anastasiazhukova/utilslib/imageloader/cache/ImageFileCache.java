package com.github.anastasiazhukova.utilslib.imageloader.cache;

import android.graphics.Bitmap;

import com.github.anastasiazhukova.utilslib.cache.file.FileCache;

import java.io.OutputStream;

public class ImageFileCache extends FileCache<Bitmap> {

    private final Bitmap.CompressFormat mCompressFormat;
    private final int mCompressQuality;

    public ImageFileCache(final Config pConfig) {
        super(pConfig);
        mCompressFormat = pConfig.getCompressFormat();
        mCompressQuality = pConfig.getCompressQuality();
    }

    @Override
    protected void write(final Bitmap pFileToCache, final OutputStream pTargetStream) {
        pFileToCache.compress(mCompressFormat, mCompressQuality, pTargetStream);
    }

    public static class Config extends FileCache.Config {

        private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.JPEG;
        private int mCompressQuality = DEFAULT_COMPRESS_QUALITY;

        private static final int DEFAULT_COMPRESS_QUALITY = 80;

        Bitmap.CompressFormat getCompressFormat() {
            return mCompressFormat;
        }

        int getCompressQuality() {
            return mCompressQuality;
        }

        public Config setCompressFormat(final Bitmap.CompressFormat pCompressFormat) {
            mCompressFormat = pCompressFormat;
            return this;
        }

        public Config setCompressQuality(final int pCompressQuality) {
            mCompressQuality = pCompressQuality;
            return this;
        }

    }
}
