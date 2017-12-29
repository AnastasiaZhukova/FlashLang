package com.github.anastasiazhukova.lib;

import android.graphics.Bitmap;
import android.provider.BaseColumns;

import com.github.anastasiazhukova.lib.cache.file.IFreeSpaceStrategy;

public interface Constants {

    final class HttpClient{

        public static final int MIN_SUCCESS_CODE = 200;
        public static final int MAX_SUCCESS_CODE = 300;
    }

    final class FileCache {

        public static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024;
        public static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024;
        public static final int BUFFER_SIZE = 4096;

    }

    final class MemoryCache {

        public static final int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024;
    }

    final class SqlConnector {

        public static final String TABLE_TAMPLATE =
                "CREATE TABLE IF NOT EXISTS %s (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,%s)";

        public static final String FOREIGN_KEY_TEMPLATE=
                "FOREIGN KEY (%s) REFERENCES %s(%s)";
    }

    final class ImageFileCache {

        public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;

        public static final int DEFAULT_COMPRESS_QUALITY = 80;
    }

    final class ImageLoader {

        public static final int NUM_OF_THREADS = 3;
    }
}
