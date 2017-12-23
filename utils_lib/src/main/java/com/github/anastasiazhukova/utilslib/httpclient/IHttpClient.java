package com.github.anastasiazhukova.utilslib.httpclient;

import com.github.anastasiazhukova.utilslib.contracts.IRequestHandler;

import java.io.IOException;
import java.io.InputStream;

public interface IHttpClient extends IRequestHandler<IHttpRequest> {

    HttpResponse getResponse(IHttpRequest pRequest) throws Exception;

    interface ResponseConverter<Response> {

        Response convert(InputStream pInputStream);
    }

    <Response> Response getResponse(IHttpRequest pRequest, ResponseConverter<Response> pResponseConverter) throws IOException;

}
