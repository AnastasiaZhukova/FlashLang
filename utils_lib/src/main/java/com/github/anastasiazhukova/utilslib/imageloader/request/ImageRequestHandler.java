package com.github.anastasiazhukova.utilslib.imageloader.request;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.github.anastasiazhukova.utilslib.contracts.IRequestHandler;
import com.github.anastasiazhukova.utilslib.imageloader.utils.ImageUtils;

public class ImageRequestHandler implements IRequestHandler<IImageRequest> {

    private static final String LOG_TAG = ImageRequestHandler.class.getSimpleName();

    @Override
    public void handle(final IImageRequest pRequest) {
        enqueue(pRequest);
    }

    private void enqueue(@NonNull final IImageRequest pImageRequest) {

        final ImageView imageView = pImageRequest.getTarget().get();

        if (imageView == null) {
            return; //if reference points to noting(i.e. it no longer exists and not going to be drawn
        }

        if (ImageUtils.imageHasSize(pImageRequest)) {
            imageView.setTag(pImageRequest.getKey());
            ImageRequestQueque.getInstance().addFirst(pImageRequest);
            dispatchLoading();
        } else {
            deferImageRequest(pImageRequest);
        }

    }

    @SuppressLint("StaticFieldLeak")
    private void dispatchLoading() {
        mLoadHandler.load();
    }

    private void deferImageRequest(final IImageRequest pImageRequest) {

        final ImageView imageView = pImageRequest.getTarget().get();

        if (imageView != null) {
            imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {

                    final ImageView view = pImageRequest.getTarget().get();

                    if (view == null) {
                        return true;
                    }

                    view.getViewTreeObserver().removeOnPreDrawListener(this);

                    if (view.getWidth() > 0 && imageView.getHeight() > 0) {
                        pImageRequest.setSize(view.getWidth(), view.getHeight());
                        enqueue(pImageRequest);
                    }

                    return true;
                }
            });
        }

    }
}
