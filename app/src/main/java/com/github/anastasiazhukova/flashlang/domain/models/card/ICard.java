package com.github.anastasiazhukova.flashlang.domain.models.card;

import com.github.anastasiazhukova.flashlang.domain.models.IIdentifiable;

public interface ICard extends IIdentifiable<String> {

    String getSourceLanguageKey();

    String getSourceText();

    String getTargetLanguageKey();

    String getTranslatedText();

}
