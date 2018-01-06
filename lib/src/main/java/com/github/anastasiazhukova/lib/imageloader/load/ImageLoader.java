package com.github.anastasiazhukova.lib.imageloader.load;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.github.anastasiazhukova.lib.Constants;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.httpclient.HttpMethod;
import com.github.anastasiazhukova.lib.httpclient.HttpRequest;
import com.github.anastasiazhukova.lib.httpclient.IHttpClient;
import com.github.anastasiazhukova.lib.imageloader.cache.ICacheManager;
import com.github.anastasiazhukova.lib.imageloader.cache.IImageFileCache;
import com.github.anastasiazhukova.lib.imageloader.cache.IImageMemoryCache;
import com.github.anastasiazhukova.lib.imageloader.request.IImageRequest;
import com.github.anastasiazhukova.lib.imageloader.request.IImageRequestQueue;
import com.github.anastasiazhukova.lib.imageloader.request.ImageRequestQueue;
import com.github.anastasiazhukova.lib.imageloader.result.IImageResponse;
import com.github.anastasiazhukova.lib.imageloader.result.ImageResponse;
import com.github.anastasiazhukova.lib.imageloader.utils.BitmapUtils;
import com.github.anastasiazhukova.lib.imageloader.utils.ImageUtils;
import com.github.anastasiazhukova.lib.logs.Log;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

import java.io.File;
import java.io.IOException;

public final class ImageLoader {

    private final IImageRequestQueue mQueue;
    private ICacheManager mCacheManager;
    private final IExecutor mExecutor;

    public ImageLoader() {
        mQueue = new ImageRequestQueue();
        mExecutor = IThreadingManager.Imlp.getThreadingManager()
                .getExecutor(Constants.ImageLoader.DEFAULT_EXECUTOR_TYPE);
    }

    public void setCacheManager(final ICacheManager pCacheManager) {
        mCacheManager = pCacheManager;
    }

    public void load(final IImageRequest pRequest) {
        enqueue(pRequest);
    }

    private void enqueue(@NonNull final IImageRequest pImageRequest) {
        final ImageView imageView = pImageRequest.getTarget().get();

        if (imageView == null && !pImageRequest.isSaved()) {
            return; //if reference points to noting(i.e. it no longer exists and not going to be drawn
        }

        if (imageView != null) {
            if (ImageUtils.imageHasSize(pImageRequest)) {
                imageView.setTag(pImageRequest.getUrl());
                mQueue.addFirst(pImageRequest);
                dispatchLoading();
            } else {
                deferImageRequest(pImageRequest);
            }
            return;
        }

        if (pImageRequest.isSaved()) {
            mQueue.addFirst(pImageRequest);
            dispatchLoading();
        }

    }

    private void dispatchLoading() {
        mExecutor.execute(new ImageLoadOperation(), new ICallback<ImageResponse>() {

            @Override
            public void onSuccess(final ImageResponse pImageResponse) {
                processImageResponse(pImageResponse);
            }

            @Override
            public void onError(final Throwable pThrowable) {
            }
        });
    }

    private void deferImageRequest(final IImageRequest pImageRequest) {

        final ImageView imageView = pImageRequest.getTarget().get();

        if (imageView != null) {
            imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {

                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);

                    final ImageView view = pImageRequest.getTarget().get();

                    if (view == null) {
                        return true;
                    }
                    if (view.getWidth() > 0 && imageView.getHeight() > 0) {
                        enqueue(pImageRequest);
                    }

                    return true;
                }
            });
        }

    }

    private void processImageResponse(final IImageResponse pResponse) {
        if (pResponse != null) {
            final IImageRequest request = pResponse.getRequest();
            if (request != null) {
                final ImageView imageView = request.getTarget().get();
                if (imageView != null) {
                    if (pResponse.getResult() != null) {
                        ImageUtils.setImage(imageView, pResponse.getResult());
                    } else {
                        ImageUtils.setImage(imageView, request.getErrorImage());
                    }
                }
            }
        }
    }

    class ImageLoadOperation implements IOperation<ImageResponse> {

        private final String LOG_TAG = ImageLoadOperation.class.getSimpleName();

        @Override
        public ImageResponse perform() {
            final ImageResponse result;
            final IImageRequest imageRequest;

            try {
                imageRequest = getRequestFromQueue();
            } catch (final InterruptedException pE) {
                result = new ImageResponse();
                result.setError(pE);
                return result;
            }

            result = new ImageResponse(imageRequest);
            Bitmap bitmap = null;

            //try get from cache
            try {
                Log.d(LOG_TAG, "doInBackground: Try get from cache");
                bitmap = getFromCache(imageRequest.getUrl());
            } catch (final IOException ignored) {
                Log.e(LOG_TAG, "doInBackground: ", ignored);
            }
            if (bitmap != null) {
                result.setResult(bitmap);
                return result;
            }

            //try load
            try {
                Log.d(LOG_TAG, "doInBackground: Try load");
                bitmap = load(imageRequest);
                if (bitmap != null) {
                    result.setResult(bitmap);
                    putInCache(result);
                } else {
                    throw new Exception("Can't load bitmap");
                }
            } catch (final Exception pE) {
                result.setError(pE);
            }

            return result;
        }

        @NonNull
        private IImageRequest getRequestFromQueue() throws NullPointerException, InterruptedException {

            final IImageRequest imageRequest = mQueue.takeFirst();
            if (imageRequest == null) {
                throw new NullPointerException("Queue is empty");
            }

            return imageRequest;
        }

        private Bitmap getFromCache(final String pKey) throws IOException {

            if (mCacheManager != null) {
                Bitmap result;
                Log.d(LOG_TAG, "getFromCache: Try get from memory cache");
                result = getFromMemoryCache(pKey);
                if (result == null) {
                    Log.d(LOG_TAG, "getFromCache: Try get from disk cache");
                    result = getFromFileCache(pKey);
                }

                return result;
            }

            return null;
        }

        private Bitmap getFromMemoryCache(final String pKey) throws IOException {
            final IImageMemoryCache memoryCache = mCacheManager.getMemoryCache();
            if (memoryCache != null) {
                return memoryCache.get(pKey);
            }

            return null;
        }

        private Bitmap getFromFileCache(final String pKey) throws IOException {
            final IImageFileCache fileCache = mCacheManager.getFileCache();
            if (fileCache != null) {
                final File file = fileCache.get(pKey);
                if (file != null) {
                    return BitmapUtils.getBitmapFromFile(file);
                }
            }
            return null;
        }

        private Bitmap load(final IImageRequest pRequest) throws IOException {

            final IHttpClient httpClient = IHttpClient.Impl.getClient();

            final HttpRequest.Builder request = new HttpRequest.Builder()
                    .setMethod(HttpMethod.GET)
                    .setUrl(pRequest.getUrl());

            return httpClient.getResponse(request.build(), new BitmapUtils.BitmapConverter(pRequest));
        }

        private void putInCache(final IImageResponse pResponse) throws IOException {
            final IImageRequest request = pResponse.getRequest();
            if (mCacheManager != null && request != null) {
                final IImageMemoryCache memoryCache = mCacheManager.getMemoryCache();
                if (memoryCache != null) {
                    memoryCache.put(request.getUrl(), pResponse.getResult());
                }
                if (request.isSaved()) {
                    final IImageFileCache fileCache = mCacheManager.getFileCache();
                    if (fileCache != null) {
                        fileCache.put(request.getUrl(), pResponse.getResult());
                    }
                }
            }
        }
    }

}


