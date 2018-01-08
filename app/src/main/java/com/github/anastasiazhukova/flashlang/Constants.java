package com.github.anastasiazhukova.flashlang;

public interface Constants {

    final class Strings {

        public static final String EMPTY_STRING = "";
    }

    final class Db {

        public static final String DB_NAME = "flashlang.db";

        public static final int DB_VERSION = 1;
    }

    final class ImageLoader {

        public static final String DISK_CACHE_DIR = "/images";

        public static final int MEMORY_CACHE_SIZE = 50 * 1024 * 1024;
    }

}
