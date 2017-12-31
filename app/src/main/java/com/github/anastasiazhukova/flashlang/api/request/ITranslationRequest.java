package com.github.anastasiazhukova.flashlang.api.request;

public interface ITranslationRequest {

    String getSourceText();

    String getSourceLanguage();

    String getTargetLanguage();

}
