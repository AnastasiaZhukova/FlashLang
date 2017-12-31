package com.github.anastasiazhukova.lib.imageloader.request;

import android.widget.ImageView;

import com.github.anastasiazhukova.lib.contracts.IRequest;

import java.lang.ref.WeakReference;

public interface IImageRequest extends IRequest {

    String getUrl();

    WeakReference<ImageView> getTarget();

    Integer getErrorImage();

    boolean isSaved();

}
