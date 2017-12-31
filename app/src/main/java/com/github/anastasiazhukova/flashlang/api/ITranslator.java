package com.github.anastasiazhukova.flashlang.api;

import com.github.anastasiazhukova.flashlang.api.models.languages.ILanguage;
import com.github.anastasiazhukova.flashlang.api.models.translation.ITranslation;
import com.github.anastasiazhukova.flashlang.api.request.ITranslationRequest;

import java.io.IOException;
import java.util.List;

public interface ITranslator {

    ITranslation translate(ITranslationRequest pRequest) throws IOException;

    List<ILanguage> getSupportedLanguages() throws RuntimeException;
}
