package com.github.anastasiazhukova.flashlang.api.response;

import java.util.List;

public interface ITranslationResponse {

    String getSourceLanguageKey();

    String getTranslatedText();

    List<String> getExamples();

}
