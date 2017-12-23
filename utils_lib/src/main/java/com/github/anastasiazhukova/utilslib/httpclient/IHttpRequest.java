package com.github.anastasiazhukova.utilslib.httpclient;

import com.github.anastasiazhukova.utilslib.contracts.IRequest;

import java.net.MalformedURLException;
import java.net.URL;

public interface IHttpRequest extends IRequest {

    URL getUrl() throws MalformedURLException;

    String getMethod();

    Headers getHeaders();

}
