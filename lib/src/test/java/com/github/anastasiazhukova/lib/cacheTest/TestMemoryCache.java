package com.github.anastasiazhukova.lib.cacheTest;

import com.github.anastasiazhukova.lib.cache.memory.MemoryCache;

public class TestMemoryCache extends MemoryCache<String> {

    public TestMemoryCache(final Config pConfig) {
        super(pConfig);
    }

    @Override
    protected int sizeOf(final String pKey, final String pValue) {
        return pKey.length() + pValue.length();
    }
}
