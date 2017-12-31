package com.github.anastasiazhukova.flashlang.api;

import com.github.anastasiazhukova.flashlang.api.request.ITranslationRequest;
import com.github.anastasiazhukova.flashlang.api.utils.UrlBuilder;
import com.github.anastasiazhukova.flashlang.mocks.TestTranslateRequest;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class UrlBuilderTest {

    private static final String CORRECT_URL =
            "https://translate.googleapis.com/translate_a/single?client=gtx&sl=ru" +
                    "&tl=en&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&q=%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82";

    @Test
    public void buildUrl() {
        final UrlBuilder builder = new UrlBuilder();
        String translateUrl = null;
        try {
            final ITranslationRequest translationRequest = new TestTranslateRequest("привет", "ru", "en");
            translateUrl = builder.getTranslateUrl(translationRequest);
        } catch (final UnsupportedEncodingException ignored) {
        }

        Assert.assertNotNull(translateUrl);
        Assert.assertEquals(CORRECT_URL, translateUrl);

    }
}