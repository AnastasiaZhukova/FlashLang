package com.github.anastasiazhukova.flashlang.api.models.translation;

import com.github.anastasiazhukova.flashlang.api.models.examples.ITranslationExample;
import com.github.anastasiazhukova.flashlang.api.models.languages.ILanguage;

import java.util.List;

public class TranslationBuilder {

    private ILanguage mSourceLanguage;
    private String mSourceText;
    private ILanguage mTargetLanguage;
    private String mTranslatedText;
    private List<ITranslationExample> mTranslationExamples;

    public TranslationBuilder setSourceLanguage(final ILanguage pSourceLanguage) {
        mSourceLanguage = pSourceLanguage;
        return this;
    }

    public TranslationBuilder setSourceText(final String pSourceText) {
        mSourceText = pSourceText;
        return this;
    }

    public TranslationBuilder setTargetLanguage(final ILanguage pTargetLanguage) {
        mTargetLanguage = pTargetLanguage;
        return this;
    }

    public TranslationBuilder setTranslatedText(final String pTranslatedText) {
        mTranslatedText = pTranslatedText;
        return this;
    }

    public TranslationBuilder setTranslationExamples(final List<ITranslationExample> pTranslationExamples) {
        mTranslationExamples = pTranslationExamples;
        return this;
    }

    public Translation createTranslation() {
        return new Translation(mSourceLanguage, mSourceText, mTargetLanguage, mTranslatedText, mTranslationExamples);
    }
}