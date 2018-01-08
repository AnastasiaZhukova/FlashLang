package com.github.anastasiazhukova.flashlang;

import android.app.Application;

import com.github.anastasiazhukova.flashlang.db.AppDb;
import com.github.anastasiazhukova.lib.cache.file.IFreeSpaceStrategy;
import com.github.anastasiazhukova.lib.context.ContextHolder;
import com.github.anastasiazhukova.lib.db.IDbOperations;
import com.github.anastasiazhukova.lib.imageloader.ILouvre;
import com.github.anastasiazhukova.lib.imageloader.Louvre;
import com.github.anastasiazhukova.lib.imageloader.cache.IImageFileCache;
import com.github.anastasiazhukova.lib.imageloader.cache.IImageMemoryCache;
import com.github.anastasiazhukova.lib.imageloader.cache.ImageFileCache;
import com.github.anastasiazhukova.lib.imageloader.cache.ImageMemoryCache;
import com.github.anastasiazhukova.lib.logs.Log;
import com.google.firebase.FirebaseApp;

public class FlashLangApp extends Application {

    @Override
    public void onCreate() {
       super.onCreate();
       init();
    }

    private void init() {
        Log.setEnabled(true);
        ContextHolder.setContext(this);
        initDb();
        initImageLoader();
        FirebaseApp.initializeApp(this);
    }

    private void initDb() {
        IDbOperations.Impl.newInstance(this, new AppDb());
    }

    private void initImageLoader() {
        final ImageFileCache.Config fileCacheConfig = new ImageFileCache.Config();
        fileCacheConfig.setCacheDirName(getCacheDir().getAbsolutePath() + Constants.ImageLoader.DISK_CACHE_DIR);
        fileCacheConfig.setFreeSpaceStrategy(new IFreeSpaceStrategy.KeepAlwaysStrategy());

        final ImageMemoryCache.Config memoryCacheConfig = new ImageMemoryCache.Config();
        memoryCacheConfig.setCacheSize(Constants.ImageLoader.MEMORY_CACHE_SIZE);

        final IImageFileCache fileCache = new ImageFileCache(fileCacheConfig);
        final IImageMemoryCache memoryCache = new ImageMemoryCache(memoryCacheConfig);

        final Louvre.Config louvreConfig = new Louvre.Config();
        louvreConfig.setFileCache(fileCache);
        louvreConfig.setMemoryCache(memoryCache);

        ILouvre.Impl.newInstance(louvreConfig);

    }

}
