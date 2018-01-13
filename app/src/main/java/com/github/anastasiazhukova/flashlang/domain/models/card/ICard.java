package com.github.anastasiazhukova.flashlang.domain.models.card;

import com.github.anastasiazhukova.flashlang.domain.models.IIdentifiable;

public interface ICard extends IIdentifiable<String> {

    String getOwnerId();

    String getSourceLanguageKey();

    String getSourceText();

    String getTargetLanguageKey();

    String getTranslatedText();

    String getPictureUrl();

}
