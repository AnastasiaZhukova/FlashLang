package com.github.anastasiazhukova.flashlang.api.request;

public class TranslationRequest implements ITranslationRequest {

    private final String mInputText;

    private final String mSourceLanguage;

    private final String mTargetLanguage;

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

    public TranslationRequest(final String pInputText, final String pSourceLanguage, final String pTargetLanguage) {
        mInputText = pInputText;
        mSourceLanguage = pSourceLanguage;
        mTargetLanguage = pTargetLanguage;
    }

}
