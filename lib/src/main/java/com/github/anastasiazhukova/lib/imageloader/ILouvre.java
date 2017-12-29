package com.github.anastasiazhukova.lib.imageloader;

import com.github.anastasiazhukova.lib.contracts.IRequestHandler;
import com.github.anastasiazhukova.lib.db.IDbOperations;
import com.github.anastasiazhukova.lib.imageloader.request.IImageRequest;
import com.github.anastasiazhukova.lib.imageloader.request.ImageRequest;

public interface ILouvre extends IRequestHandler<IImageRequest> {

    ImageRequest.Builder newRequest();

}
