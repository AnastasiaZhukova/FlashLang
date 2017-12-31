package com.github.anastasiazhukova.lib.httpclient;

import com.github.anastasiazhukova.lib.contracts.IRequestHandler;
import com.github.anastasiazhukova.lib.contracts.IResponseConverter;

import java.io.IOException;

public interface IHttpClient extends IRequestHandler<IHttpRequest> {

    IHttpResponse getResponse(IHttpRequest pRequest) throws Exception;

    <Response> Response getResponse(IHttpRequest pRequest, IResponseConverter<Response> pResponseConverter) throws IOException;

    final class Impl {

        public static IHttpClient getClient() {
            return new HttpClient();
        }
    }
}
