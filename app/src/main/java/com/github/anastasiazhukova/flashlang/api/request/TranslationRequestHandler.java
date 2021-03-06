package com.github.anastasiazhukova.flashlang.api.request;

import com.github.anastasiazhukova.flashlang.api.parser.TranslationParser;
import com.github.anastasiazhukova.flashlang.api.response.ITranslationResponse;
import com.github.anastasiazhukova.flashlang.api.utils.UrlBuilder;
import com.github.anastasiazhukova.lib.httpclient.HttpMethod;
import com.github.anastasiazhukova.lib.httpclient.HttpRequest;
import com.github.anastasiazhukova.lib.httpclient.IHttpClient;

public class TranslationRequestHandler implements ITranslationRequestHandler {

    @Override
    public ITranslationResponse[] handle(final ITranslationRequest pRequest) throws Exception {
        if (pRequest != null) {

            final UrlBuilder builder = new UrlBuilder();
            final String translateUrl = builder.getTranslateUrl(pRequest.getApiKey(), pRequest);

            final HttpRequest.Builder httpRequestBuilder = new HttpRequest.Builder();
            httpRequestBuilder.setUrl(translateUrl).setMethod(HttpMethod.GET);

            final ITranslationResponse[] response =
                    IHttpClient.Impl.getClient().getResponse(httpRequestBuilder.build(), new TranslationParser());
            return response;
        }
        return null;
    }

}
