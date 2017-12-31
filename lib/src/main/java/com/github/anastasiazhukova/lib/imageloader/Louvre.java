package com.github.anastasiazhukova.lib.imageloader;

import com.github.anastasiazhukova.lib.imageloader.cache.CacheManager;
import com.github.anastasiazhukova.lib.imageloader.cache.IImageFileCache;
import com.github.anastasiazhukova.lib.imageloader.cache.IImageMemoryCache;
import com.github.anastasiazhukova.lib.imageloader.load.ImageLoader;
import com.github.anastasiazhukova.lib.imageloader.request.IImageRequest;
import com.github.anastasiazhukova.lib.imageloader.request.ImageRequest;

import org.jetbrains.annotations.NotNull;

public final class Louvre implements ILouvre {

    private static volatile Louvre INSTANCE;
    private static final Object lock = new Object();

    private final ImageLoader mImageLoader;

    private Louvre() {
        mImageLoader = new ImageLoader();
    }

    public static Louvre getInstance() {
        if (INSTANCE == null) {
            synchronized (lock) {
                INSTANCE = new Louvre();
            }
        }
        return INSTANCE;
    }

    public void setConfig(final Config pConfig) {
        mImageLoader.setCacheManager(new CacheManager(pConfig.mMemoryCache, pConfig.mFileCache));
    }

    @Override
    public ImageRequest.Builder newRequest() {
        return new ImageRequest.Builder();
    }

    @Override
    public void handle(@NotNull final IImageRequest pRequest) {
        mImageLoader.load(pRequest);
    }

    public static class Config {

        IImageFileCache mFileCache;
        IImageMemoryCache mMemoryCache;

        public void setFileCache(@NotNull final IImageFileCache pFileCache) {
            mFileCache = pFileCache;
        }

        public void setMemoryCache(@NotNull final IImageMemoryCache pMemoryCache) {
            mMemoryCache = pMemoryCache;
        }

    }

}
