package com.github.anastasiazhukova.lib.imageloader.result;

import android.graphics.Bitmap;

import com.github.anastasiazhukova.lib.contracts.IResponse;
import com.github.anastasiazhukova.lib.imageloader.request.IImageRequest;

public interface IImageResponse extends IResponse<Bitmap> {

    IImageRequest getRequest();
}
