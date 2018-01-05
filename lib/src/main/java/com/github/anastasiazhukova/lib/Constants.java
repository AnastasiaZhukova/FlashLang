package com.github.anastasiazhukova.lib;

import android.graphics.Bitmap;

public interface Constants {

    final class HttpClient {

        public static final int MIN_SUCCESS_CODE = 200;
        public static final int MAX_SUCCESS_CODE = 300;
    }

    final class FileCache {

        public static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024;
        public static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024;
        public static final int BUFFER_SIZE = 4096;
        public static final int PERCENT_OF_TOTAL_SPACE = 1 / 50; //i.e. 2%
    }

    final class MemoryCache {

        public static final int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024;
    }

    final class Sql {

        public static final String TABLE_TEMPLATE =
                "CREATE TABLE IF NOT EXISTS %s (%s)";

        public static final String FOREIGN_KEY_TEMPLATE =
                "FOREIGN KEY (%s) REFERENCES %s(%s)";

        public static final char WORD_SEPARATOR = ' ';

        public static final char STATEMENT_SEPARATOR = ',';

        public static final String PRIMARY_KEY = "PRIMARY KEY";

        public static final String NOT_NULL = "NOT NULL";

        public static final String WHERE_EQUAL_TEMPLATE = "%s = '%s'";

        public static final String WHERE_OR_TEMPLATE = "%s = '%s' OR %s = '%s'";

        public static final String WHERE_AND_TEMPLATE = "%s = '%s' AND %s = '%s'";

        public static final String WHERE_LIKE_TEMPLATE = "%s LIKE '%s'";
    }

    final class ImageFileCache {

        public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;

        public static final int DEFAULT_COMPRESS_QUALITY = 80;
    }

    final class ImageLoader {

        public static final int NUM_OF_THREADS = 3;
    }
}
