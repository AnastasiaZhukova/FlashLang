package com.github.anastasiazhukova.flashlang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.anastasiazhukova.lib.cache.memory.MemoryCache;
import com.github.anastasiazhukova.lib.httpclient.HttpClient;
import com.github.anastasiazhukova.lib.httpclient.IHttpClient;
import com.github.anastasiazhukova.lib.imageloader.ILouvre;
import com.github.anastasiazhukova.lib.imageloader.Louvre;
import com.github.anastasiazhukova.lib.imageloader.cache.ImageFileCache;
import com.github.anastasiazhukova.lib.imageloader.cache.ImageMemoryCache;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO image loader needs to be initialized in the Application
        Louvre.Config config = new Louvre.Config();

        config.setFileCache(new ImageFileCache((ImageFileCache.Config) new ImageFileCache.Config().setCacheDirName(getCacheDir().getAbsolutePath())));
        config.setMemoryCache(new ImageMemoryCache(new MemoryCache.Config()));

        Louvre.getInstance().setConfig(config);

        try {
            load();
        } catch (final Exception pE) {
            Toast.makeText(this, "Error : " + pE.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void load() throws Exception {
        final ImageView imageView = findViewById(R.id.image_view);

        Louvre.getInstance()
                .newRequest()
                .from("http://www.phtheme.com/androidimg/allimg/160629/106-1606292326310-L.jpg")
                .to(imageView)
                .saved(true)
                .load();
    }
}


