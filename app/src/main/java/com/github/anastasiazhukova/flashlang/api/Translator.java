package com.github.anastasiazhukova.flashlang.api;

import android.content.Context;

import com.github.anastasiazhukova.flashlang.api.models.examples.ITranslationExample;
import com.github.anastasiazhukova.flashlang.api.models.examples.TranslationExample;
import com.github.anastasiazhukova.flashlang.api.models.languages.ILanguage;
import com.github.anastasiazhukova.flashlang.api.models.languages.Language;
import com.github.anastasiazhukova.flashlang.api.models.translation.ITranslation;
import com.github.anastasiazhukova.flashlang.api.models.translation.TranslationBuilder;
import com.github.anastasiazhukova.flashlang.api.request.ITranslationRequest;
import com.github.anastasiazhukova.flashlang.api.response.ITranslationResponse;
import com.github.anastasiazhukova.flashlang.api.response.TranslationParser;
import com.github.anastasiazhukova.flashlang.api.utils.LanguageUtils;
import com.github.anastasiazhukova.flashlang.api.utils.UrlBuilder;
import com.github.anastasiazhukova.lib.context.ContextHolder;
import com.github.anastasiazhukova.lib.httpclient.HttpMethod;
import com.github.anastasiazhukova.lib.httpclient.HttpRequest;
import com.github.anastasiazhukova.lib.httpclient.IHttpClient;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator implements ITranslator {

    private Map<ApiConstants.LanguageKeys, ILanguage> mSupportedLanguages;

    public Translator(final Context pContext) {
        mSupportedLanguages = getSupportedLanguages(pContext);
    }

    @Override
    public ITranslation translate(final ITranslationRequest pRequest) throws IOException {
        final ITranslationResponse translationResponse = makeTranslation(pRequest);
        return buildTranslation(pRequest, translationResponse);
    }

    @Override
    public List<ILanguage> getSupportedLanguages() throws RuntimeException {
        if (mSupportedLanguages == null) {
            mSupportedLanguages = getSupportedLanguages(ContextHolder.getContext());
        }
        return new ArrayList<>(mSupportedLanguages.values());
    }

    private Map<ApiConstants.LanguageKeys, ILanguage> getSupportedLanguages(final Context pContext) {
        if (mSupportedLanguages != null) {
            return mSupportedLanguages;
        }

        final Context context;
        if (pContext != null) {
            context = pContext;
        } else {
            context = ContextHolder.getContext();
        }
        if (context == null) {
            throw new RuntimeException("Can't get context");
        }

        mSupportedLanguages = new HashMap<>();
        for (final ApiConstants.LanguageKeys key :
                ApiConstants.LanguageKeys.values()) {
            final ILanguage language = new Language(key.name(), LanguageUtils.getLanguageName(context, key.name()));
            mSupportedLanguages.put(key, language);
        }

        return mSupportedLanguages;

    }

    private ITranslationResponse makeTranslation(final ITranslationRequest pRequest) throws IOException {
        if (pRequest != null) {

            final UrlBuilder builder = new UrlBuilder();
            final String translateUrl = builder.getTranslateUrl(pRequest);

            final HttpRequest.Builder httpRequestBuilder = new HttpRequest.Builder();
            httpRequestBuilder.setUrl(translateUrl).setMethod(HttpMethod.GET);

            ITranslationResponse response =
                    IHttpClient.Impl.getClient().getResponse(httpRequestBuilder.build(), new TranslationParser());
            return response;
        }
        return null;
    }

    private ITranslation buildTranslation(final ITranslationRequest pRequest, final ITranslationResponse pResponse) {

        if (pRequest != null && pResponse != null) {

            final ILanguage sourceLanguage = mSupportedLanguages.get(ApiConstants.LanguageKeys.valueOf(pRequest.getSourceLanguage()));
            final ILanguage targetLanguage = mSupportedLanguages.get(ApiConstants.LanguageKeys.valueOf(pRequest.getTargetLanguage()));
            final List<String> translateExamples = pResponse.getExamples();
            List<ITranslationExample> examples = null;
            if (translateExamples != null && !translateExamples.isEmpty()) {
                examples = new ArrayList<>();
                for (final String example :
                        pResponse.getExamples()) {
                    examples.add(new TranslationExample(example, pRequest.getSourceText()));
                }
            }

            final ITranslation translation = new TranslationBuilder()
                    .setSourceLanguage(sourceLanguage)
                    .setSourceText(pRequest.getSourceText())
                    .setTargetLanguage(targetLanguage)
                    .setTranslatedText(pResponse.getTranslatedText())
                    .setTranslationExamples(examples)
                    .createTranslation();
            return translation;
        }

        return null;
    }

}
