package com.github.anastasiazhukova.utilslib.imageloader.request;

import android.widget.ImageView;

import com.github.anastasiazhukova.utilslib.contracts.IRequest;

import java.lang.ref.WeakReference;

public interface IImageRequest extends IRequest {

    String getUrl();

    WeakReference<ImageView> getTarget();

    Integer getErrorImage();

    boolean isCached();

}
