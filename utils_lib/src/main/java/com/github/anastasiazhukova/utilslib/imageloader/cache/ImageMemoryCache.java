package com.github.anastasiazhukova.utilslib.imageloader.cache;

import android.graphics.Bitmap;

import com.github.anastasiazhukova.utilslib.cache.memory.MemoryCache;

import java.util.BitSet;

public class ImageMemoryCache extends MemoryCache<Bitmap> {

    public ImageMemoryCache(final Config pConfig) {
        super(pConfig);
    }
}
