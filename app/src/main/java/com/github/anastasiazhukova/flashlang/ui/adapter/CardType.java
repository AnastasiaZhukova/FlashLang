package com.github.anastasiazhukova.flashlang.ui.adapter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({CardType.ROW, CardType.NO_IMAGE, CardType.WITH_IMAGE})
@Retention(RetentionPolicy.SOURCE)
public @interface CardType {

    int ROW = 1;
    int NO_IMAGE = 2;
    int WITH_IMAGE = 3;
}