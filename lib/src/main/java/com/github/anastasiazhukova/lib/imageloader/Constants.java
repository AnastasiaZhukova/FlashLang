package com.github.anastasiazhukova.lib.imageloader;

import android.graphics.Bitmap;

public interface Constants {

    final class ImageMemoryCache {

        public static final int DEFAILT_CACHE_SIZE = 50 * 1024 * 1024;
    }

    final class ImageFileCache {

        public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;

        public static final int DEFAULT_COMPRESS_QUALITY = 80;
    }

    final class ImageLoader {

        public static final int NUM_OF_THREADS = 3;
    }

}
