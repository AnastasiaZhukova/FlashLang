package com.github.anastasiazhukova.flashlang.api.models.translation;

import com.github.anastasiazhukova.flashlang.api.models.examples.ITranslationExample;
import com.github.anastasiazhukova.flashlang.api.models.languages.ILanguage;

import java.util.List;

public class Translation implements ITranslation {

    private final ILanguage mSourceLanguage;
    private final String mSourceText;
    private final ILanguage mTargetLanguage;
    private final String mTranslatedText;
    private final List<ITranslationExample> mTranslationExamples;

    Translation(final ILanguage pSourceLanguage, final String pSourceText, final ILanguage pTargetLanguage, final String pTranslatedText, final List<ITranslationExample> pTranslationExamples) {
        mSourceLanguage = pSourceLanguage;
        mSourceText = pSourceText;
        mTargetLanguage = pTargetLanguage;
        mTranslatedText = pTranslatedText;
        mTranslationExamples = pTranslationExamples;
    }

    @Override
    public String getSourceText() {
        return mSourceText;
    }

    @Override
    public ILanguage getSourceLanguage() {
        return mSourceLanguage;
    }

    @Override
    public ILanguage getTargetLanguage() {
        return mTargetLanguage;
    }

    @Override
    public String getTranslatedText() {
        return mTranslatedText;
    }

    @Override
    public List<ITranslationExample> getExamples() {
        return mTranslationExamples;
    }
}
