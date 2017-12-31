package com.github.anastasiazhukova.flashlang.api.models.translation;

import com.github.anastasiazhukova.flashlang.api.models.examples.ITranslationExample;
import com.github.anastasiazhukova.flashlang.api.models.languages.ILanguage;

import java.util.List;

public interface ITranslation {

    String getSourceText();

    ILanguage getSourceLanguage();

    ILanguage getTargetLanguage();

    String getTranslatedText();

    List<ITranslationExample> getExamples();

}
