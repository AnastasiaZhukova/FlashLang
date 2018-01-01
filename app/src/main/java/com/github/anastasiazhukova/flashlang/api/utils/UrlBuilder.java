package com.github.anastasiazhukova.flashlang.api.utils;

import com.github.anastasiazhukova.flashlang.api.ApiConstants;
import com.github.anastasiazhukova.flashlang.api.request.ITranslationRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlBuilder {

    public String getTranslateUrl(final String pApiKey, final ITranslationRequest pRequest) throws UnsupportedEncodingException {
        final String sourceText = URLEncoder.encode(pRequest.getSourceText(), "UTF-8");
        final String url = String.format(ApiConstants.Url.TRANSLATION_URL_TEMPLATE,
                pApiKey, pRequest.getSourceLanguage(), pRequest.getTargetLanguage(), sourceText);
        return url;
    }

}
