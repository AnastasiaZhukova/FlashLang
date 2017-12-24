package com.github.anastasiazhukova.lib.httpclient;

import com.github.anastasiazhukova.lib.contracts.IResponse;

public interface IHttpResponse extends IResponse<String> {

    IHttpRequest getRequest();


}
