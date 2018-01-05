package com.github.anastasiazhukova.flashlang.models;

public class CardBuilder {

    private String mId;
    private String mSourceLanguageKey;
    private String mSourceText;
    private String mTargetLanguage;
    private String mTranslatedText;

    public CardBuilder setId(final String pId) {
        mId = pId;
        return this;
    }

    public String getId() {
        return mId;
    }

    public String getSourceLanguageKey() {
        return mSourceLanguageKey;
    }

    public String getSourceText() {
        return mSourceText;
    }

    public String getTargetLanguage() {
        return mTargetLanguage;
    }

    public String getTranslatedText() {
        return mTranslatedText;
    }

    public CardBuilder setSourceLanguageKey(final String pSourceLanguageKey) {
        mSourceLanguageKey = pSourceLanguageKey;
        return this;
    }

    public CardBuilder setSourceText(final String pSourceText) {
        mSourceText = pSourceText;
        return this;
    }

    public CardBuilder setTargetLanguage(final String pTargetLanguage) {
        mTargetLanguage = pTargetLanguage;
        return this;
    }

    public CardBuilder setTranslatedText(final String pTranslatedText) {
        mTranslatedText = pTranslatedText;
        return this;
    }

    public Card createCard() {
        return new Card(this);
    }
}