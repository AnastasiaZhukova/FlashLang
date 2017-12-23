package com.github.anastasiazhukova.utilslib.cache.memory;

import android.util.LruCache;

import com.github.anastasiazhukova.utilslib.cache.ICache;
import com.github.anastasiazhukova.utilslib.cache.MD5;

public abstract class MemoryCache<TFile> implements ICache<String, TFile, TFile> {

    private final LruCache<String, TFile> mCache;
    private final Object lock = new Object();

    public MemoryCache(final Config pConfig) {
        final int cacheSize;
        if (pConfig != null) {
            cacheSize = pConfig.getCacheSize();
        } else {
            cacheSize = 0;

        }
        mCache = new LruCache<>(cacheSize);
    }

    @Override
    public TFile get(final String pKey) {
        synchronized (lock) {
            return mCache.get(MD5.hash(pKey));
        }
    }

    @Override
    public void put(final String pKey, final TFile pFile) {
        synchronized (lock) {
            mCache.put(MD5.hash(pKey), pFile);
        }
    }

    public static class Config {

        private int mCacheSize;

        int getCacheSize() {
            return mCacheSize;
        }

        public Config setCacheSize(final int pCacheSize) {
            mCacheSize = pCacheSize;
            return this;
        }
    }
}
