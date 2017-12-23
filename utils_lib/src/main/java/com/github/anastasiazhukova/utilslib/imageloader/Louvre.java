package com.github.anastasiazhukova.utilslib.imageloader;

import com.github.anastasiazhukova.utilslib.imageloader.cache.ImageFileCache;
import com.github.anastasiazhukova.utilslib.imageloader.cache.ImageMemoryCache;
import com.github.anastasiazhukova.utilslib.imageloader.request.IImageRequest;
import com.github.anastasiazhukova.utilslib.imageloader.request.ImageRequest;

public class Louvre implements ILouvre {

    private static volatile Louvre INSTANCE;
    private static final Object lock = new Object();

    ImageFileCache mFileCache;
    ImageMemoryCache mMemoryCache;

    private Louvre() {
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
        mFileCache = pConfig.getFileCache();
        mMemoryCache = pConfig.getMemoryCache();
    }

    @Override
    public IImageRequest newRequest() {
        return new ImageRequest();
    }

    @Override
    public void load(final IImageRequest pImageRequest){

    }

    public static class Config {

        ImageFileCache mFileCache;
        ImageMemoryCache mMemoryCache;

        public void setFileCache(final ImageFileCache pFileCache) {
            mFileCache = pFileCache;
        }

        public void setMemoryCache(final ImageMemoryCache pMemoryCache) {
            mMemoryCache = pMemoryCache;
        }

        ImageFileCache getFileCache() {
            return mFileCache;

        }

        ImageMemoryCache getMemoryCache() {
            return mMemoryCache;
        }
    }

}
