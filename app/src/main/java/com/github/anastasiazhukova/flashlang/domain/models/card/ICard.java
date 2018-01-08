package com.github.anastasiazhukova.flashlang.domain.models.card;

public interface ICard {

    String getSourceLanguageKey();

    String getSourceText();

    String getTargetLanguageKey();

    String getTranslatedText();

}
