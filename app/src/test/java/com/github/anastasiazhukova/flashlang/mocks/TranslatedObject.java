package com.github.anastasiazhukova.flashlang.mocks;

public class TranslatedObject {

    private String mTranslation;
    private String mSourceText;
    private String mSourceLanguage;
    private String[] mExamples;

    public String getTranslation() {
        return mTranslation;
    }

    public void setTranslation(final String pTranslation) {
        mTranslation = pTranslation;
    }

    public String getSourceText() {
        return mSourceText;
    }

    public void setSourceText(final String pSourceText) {
        mSourceText = pSourceText;
    }

    public String[] getExamples() {
        return mExamples;
    }

    public void setExamples(final String[] pExamples) {
        mExamples = pExamples;
    }

    public String getSourceLanguage() {
        return mSourceLanguage;
    }

    public void setSourceLanguage(final String pSourceLanguage) {
        mSourceLanguage = pSourceLanguage;
    }
}
