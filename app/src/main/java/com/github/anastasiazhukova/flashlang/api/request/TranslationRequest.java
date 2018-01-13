package com.github.anastasiazhukova.flashlang.api.request;

import com.github.anastasiazhukova.flashlang.api.ApiConstants;

public class TranslationRequest implements ITranslationRequest {

    private final String mInputText;

    private final String mSourceLanguage;

    private final String mTargetLanguage;

    private final String mApiKey;

    public TranslationRequest(final String pApiKey, final String pInputText, final String pSourceLanguageKey, final String pTargetLanguageKey) {
        mApiKey = pApiKey;
        mInputText = pInputText;
        mSourceLanguage = pSourceLanguageKey;
        mTargetLanguage = pTargetLanguageKey;
    }

    public TranslationRequest(final String pApiKey, final String pInputText, final ApiConstants.LanguageKeys pSourceLanguageKey, final ApiConstants.LanguageKeys pTargetLanguageKey) {
        this(pApiKey, pInputText, pSourceLanguageKey.name(), pTargetLanguageKey.name());
    }

    @Override
    public String getSourceText() {
        return mInputText;
    }

    @Override
    public String getSourceLanguage() {
        return mSourceLanguage;
    }

    @Override
    public String getTargetLanguage() {
        return mTargetLanguage;
    }

    @Override
    public String getApiKey() {
        return null;
    }

}
