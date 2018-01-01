package com.github.anastasiazhukova.flashlang.models;

public interface ICard extends IIdentifiable<String> {

    String getSourceLanguageKey();

    String getSourceText();

    String getTargetLanguageKey();

    String getTranslatedText();

}
