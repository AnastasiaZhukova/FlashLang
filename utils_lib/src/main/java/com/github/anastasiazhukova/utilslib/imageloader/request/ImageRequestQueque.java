package com.github.anastasiazhukova.utilslib.imageloader.request;

import android.support.annotation.NonNull;

import com.github.anastasiazhukova.utilslib.contracts.IRequestQueque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ImageRequestQueque implements IRequestQueque<IImageRequest> {

    private final BlockingDeque<IImageRequest> mQueque;

    public ImageRequestQueque() {

        mQueque = new LinkedBlockingDeque<>();
    }

    @Override
    public IImageRequest takeFirst() throws InterruptedException {
        return mQueque.takeFirst();
    }

    @Override
    public void addFirst(@NonNull final IImageRequest pImageRequest) {
        mQueque.addFirst(pImageRequest);
    }

}
