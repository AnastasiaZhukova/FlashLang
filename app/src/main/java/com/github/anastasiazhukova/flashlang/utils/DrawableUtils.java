package com.github.anastasiazhukova.flashlang.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.github.anastasiazhukova.lib.context.ContextHolder;

public class DrawableUtils {

    public static Bitmap bitmapFromDrawable(int pId) {
        return ((BitmapDrawable) ContextHolder.getContext().getResources().getDrawable(pId)).getBitmap();
    }

}
