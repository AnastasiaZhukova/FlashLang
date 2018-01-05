package com.github.anastasiazhukova.flashlang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.anastasiazhukova.lib.imageloader.ILouvre;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            load();
        } catch (final Exception pE) {
            Toast.makeText(this, "Error : " + pE.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void load() throws Exception {
        final ImageView imageView = findViewById(R.id.image_view);

        ILouvre.Impl.getInstance()
                .newRequest()
                .from("https://i.pinimg.com/236x/b6/7f/3f/b67f3fd18f48bd2bf96a43121499d7fc--cellphone-wallpapers-wallpapers-android.jpg")
                .to(imageView)
                .saved(true)
                .load();
    }
}


