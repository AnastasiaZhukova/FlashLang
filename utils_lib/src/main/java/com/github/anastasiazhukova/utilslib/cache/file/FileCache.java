package com.github.anastasiazhukova.utilslib.cache.file;

import android.os.StatFs;

import com.github.anastasiazhukova.utilslib.cache.ICache;
import com.github.anastasiazhukova.utilslib.cache.MD5;
import com.github.anastasiazhukova.utilslib.io.IOUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;

public abstract class FileCache<PFile> implements ICache<String, File, PFile> {

    private final File mCacheDir;
    private final long mCacheSize;

    private final IFreeSpaceStrategy mFreeSpaceStrategy;

    private static final int BUFFER_SIZE = 4096;

    protected FileCache(final Config pConfig) {
        if (pConfig != null) {
            final String cacheDirName = pConfig.getCacheDir();
            if (cacheDirName == null || cacheDirName.isEmpty()) {
                throw new IllegalStateException("Wrong cache dir");
            }
            final File cacheDir = new File(cacheDirName);
            mCacheDir = cacheDir;
            if (!mCacheDir.exists()) {
                final boolean mkdir = mCacheDir.mkdirs();
                if (!mkdir) {
                    throw new IllegalStateException("Can't create dir " + cacheDir.getName());
                }
            }

            mCacheDir.setWritable(true);

            if (!mCacheDir.canWrite()) {
                throw new IllegalStateException("Can't write to dir " + mCacheDir.getName());
            }

            mCacheSize = calculateDiskCacheSize(mCacheDir, pConfig);
            mFreeSpaceStrategy = pConfig.getFreeSpaceStrategy();

            freeSpaceIfRequired();
        } else {
            throw new IllegalStateException("Wrong disk cache config");
        }

    }

    @Override
    public final File get(final String pUri) {
        final String fileName = MD5.hash(pUri);
        final File[] files = mCacheDir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return fileName.equals(name);
            }
        });
        if (files != null && files.length == 1) {
            return files[0];
        } else {
            return null;
        }
    }

    @Override
    public final void put(final String pUri, final PFile pFile) throws IOException {

        final String fileName = MD5.hash(pUri);
        File file = get(fileName);
        if (file == null) {
            file = createNewFile(fileName);
        }
        if (file != null) {
            final FileOutputStream outputStream = new FileOutputStream(file);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, BUFFER_SIZE);
            try {
                write(pFile, bufferedOutputStream);
                file.setLastModified(System.currentTimeMillis());
            } finally {
                IOUtils.close(outputStream);
                IOUtils.close(bufferedOutputStream);
            }
        }

        freeSpaceIfRequired();

    }

    protected abstract void write(PFile pFileToCache, OutputStream pTargetStream) throws IOException;

    private File createNewFile(final String pFileName) throws IOException {
        final File file = new File(mCacheDir, pFileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    private void freeSpaceIfRequired() {
        final long currentCacheSize = getCurrentCacheSize();
        if (currentCacheSize > mCacheSize) {
            final File[] files = mCacheDir.listFiles();
            mFreeSpaceStrategy.freeSpace(files, currentCacheSize, mCacheSize);
        }
    }

    private long getCurrentCacheSize() {
        long currentSize = mCacheDir.length();
        final File[] files = mCacheDir.listFiles();
        for (final File f : files) {
            currentSize += f.length();
        }
        return currentSize;
    }

    private long calculateDiskCacheSize(final File dir, final Config pConfig) {

        final int minDiskCacheSize = pConfig.getMinDiskCacheSize();
        final int maxDiskCacheSize = pConfig.getMaxDiskCacheSize();

        long size = minDiskCacheSize;

        try {
            final StatFs statFs = new StatFs(dir.getAbsolutePath());
            final long available = ((long) statFs.getBlockCount()) * statFs.getBlockSize();
            size = available / 50; //fixme magic number
        } catch (final IllegalArgumentException ignored) {
        }

        return Math.max(Math.min(size, maxDiskCacheSize), minDiskCacheSize);
    }

    public abstract static class Config {

        private String mCacheDir;
        private int mMinDiskCacheSize = MIN_DISK_CACHE_SIZE;
        private int mMaxDiskCacheSize = MAX_DISK_CACHE_SIZE;
        private IFreeSpaceStrategy mFreeSpaceStrategy = DEFAULT_FREE_SPACE_STRATEGY;

        private static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024;
        private static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024;
        private static final IFreeSpaceStrategy DEFAULT_FREE_SPACE_STRATEGY = new IFreeSpaceStrategy.LastModifiedStrategy();

        String getCacheDir() {
            return mCacheDir;
        }

        int getMinDiskCacheSize() {
            return mMinDiskCacheSize;
        }

        int getMaxDiskCacheSize() {
            return mMaxDiskCacheSize;
        }

        IFreeSpaceStrategy getFreeSpaceStrategy() {
            return mFreeSpaceStrategy;
        }

        public Config setCacheDirName(final String pCacheDirName) {
            mCacheDir = pCacheDirName;
            return this;
        }

        public Config setMinDiskCacheSize(final int pMinDiskCacheSize) {
            mMinDiskCacheSize = pMinDiskCacheSize;
            return this;
        }

        public Config setMaxDiskCacheSize(final int pMaxDiskCacheSize) {
            mMaxDiskCacheSize = pMaxDiskCacheSize;
            return this;
        }

        public Config setFreeSpaceStrategy(final IFreeSpaceStrategy pFreeSpaceStrategy) {
            mFreeSpaceStrategy = pFreeSpaceStrategy;
            return this;
        }

    }

}
