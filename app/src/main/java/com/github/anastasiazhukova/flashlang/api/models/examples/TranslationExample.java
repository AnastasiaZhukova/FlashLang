package com.github.anastasiazhukova.flashlang.api.models.examples;

public class TranslationExample implements ITranslationExample {

    private String mExample;
    private String mTargetWord;

    public TranslationExample(final String pExample, final String pTargetWord) {
        mExample = pExample;
        mTargetWord = pTargetWord;
    }

    @Override
    public String getFullString() {
        return null;
    }

    @Override
    public String getTargetWord() {
        return null;
    }
}
