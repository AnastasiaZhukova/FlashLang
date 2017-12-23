package com.github.anastasiazhukova.utilslib.cache.file;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public interface IFreeSpaceStrategy {

    void freeSpace(File[] pFiles, long currentCacheSize, long requiredCacheSize);

    class LastModifiedStrategy implements IFreeSpaceStrategy, Comparator<File> {

        @Override
        public void freeSpace(final File[] pFiles, long pCurrentCacheSize, final long pRequiredCacheSize) {
            Arrays.sort(pFiles, this);
            int i = 0;
            do {
                final File f = pFiles[i];
                final long length = f.length();
                if (f.delete()) {
                    pCurrentCacheSize -= length;
                }
                i++;
            } while (pCurrentCacheSize > pRequiredCacheSize);
        }

        @Override
        public int compare(final File o1, final File o2) {
            return Long.valueOf(o1.lastModified()).compareTo(o2.lastModified());
        }
    }

    class KeepAlwaysStrategy implements IFreeSpaceStrategy {

        @Override
        public void freeSpace(final File[] pFiles, final long currentCacheSize, final long requiredCacheSize) {
            //ignored due to strategy meaning
        }
    }

}
