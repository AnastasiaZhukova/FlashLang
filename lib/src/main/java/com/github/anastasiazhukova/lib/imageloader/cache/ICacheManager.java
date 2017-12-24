package com.github.anastasiazhukova.lib.imageloader.cache;

public interface ICacheManager {

    IImageMemoryCache getMemoryCache();

    IImageFileCache getFileCache();
}
