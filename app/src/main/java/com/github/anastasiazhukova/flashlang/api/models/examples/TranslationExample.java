package com.github.anastasiazhukova.flashlang.api.models.examples;

public class TranslationExample implements ITranslationExample {

    private final String mExample;
    private final String mTargetWord;

    public TranslationExample(final String pExample, final String pTargetWord) {
        mExample = pExample;
        mTargetWord = pTargetWord;
    }

    @Override
    public String getFullString() {
        return mExample;
    }

    @Override
    public String getTargetWord() {
        return mTargetWord;
    }
}
