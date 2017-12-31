package com.github.anastasiazhukova.flashlang.api.response;

import java.util.List;

public class TranslationResponse implements ITranslationResponse {

    private String mSourceLanguage;
    private String mTranslatedText;
    private List<String> mExamples;

    @Override
    public String getSourceLanguageKey() {
        return mSourceLanguage;
    }

    @Override
    public String getTranslatedText() {
        return mTranslatedText;
    }

    @Override
    public List<String> getExamples() {
        return mExamples;
    }

    public void setSourceLanguage(final String pSourceLanguage) {
        mSourceLanguage = pSourceLanguage;
    }

    public void setTranslatedText(final String pTranslatedText) {
        mTranslatedText = pTranslatedText;
    }

    public void setExamples(final List<String> pExamples) {
        mExamples = pExamples;
    }
}
