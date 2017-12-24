package com.github.anastasiazhukova.lib.cache;

import com.github.anastasiazhukova.lib.cache.file.IFreeSpaceStrategy;

public interface Constants {

    final class FileCache {

        public static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024;
        public static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024;
        public static final IFreeSpaceStrategy DEFAULT_FREE_SPACE_STRATEGY = new IFreeSpaceStrategy.LastModifiedStrategy();

    }

    final class MemoryCache {

        public static final int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024;
    }

}
