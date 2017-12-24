package com.github.anastasiazhukova.lib.httpclient;

import com.github.anastasiazhukova.lib.contracts.IRequest;

import java.net.MalformedURLException;
import java.net.URL;

interface IHttpRequest extends IRequest {

    URL getUrl() throws MalformedURLException;

    String getMethod();

    Headers getHeaders();

}
