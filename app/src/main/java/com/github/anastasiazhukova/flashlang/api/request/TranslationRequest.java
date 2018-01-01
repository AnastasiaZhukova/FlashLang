package com.github.anastasiazhukova.flashlang.api.request;

import com.github.anastasiazhukova.flashlang.api.ApiConstants;

public class TranslationRequest implements ITranslationRequest {

    private final String mInputText;

    private final String mSourceLanguage;

    private final String mTargetLanguage;

    public TranslationRequest(final String pInputText, final String pSourceLanguage, final String pTargetLanguage) {
        mInputText = pInputText;
        mSourceLanguage = pSourceLanguage;
        mTargetLanguage = pTargetLanguage;
    }

    public TranslationRequest(final String pInputText, final ApiConstants.LanguageKeys pSourceLanguage, final ApiConstants.LanguageKeys pTargetLanguage) {
        this(pInputText, pSourceLanguage.name(), pTargetLanguage.name());
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

}
