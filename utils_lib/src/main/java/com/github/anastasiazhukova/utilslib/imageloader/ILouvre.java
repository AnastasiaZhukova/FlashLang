package com.github.anastasiazhukova.utilslib.imageloader;

import com.github.anastasiazhukova.utilslib.imageloader.request.IImageRequest;

public interface ILouvre {

    IImageRequest newRequest();

    void load(IImageRequest pImageRequest);

}
