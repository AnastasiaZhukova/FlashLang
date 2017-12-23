package com.github.anastasiazhukova.utilslib.httpclient;

import com.github.anastasiazhukova.utilslib.contracts.IResponse;

public interface IHttpResponse extends IResponse<String> {

    IHttpRequest getRequest();
}
