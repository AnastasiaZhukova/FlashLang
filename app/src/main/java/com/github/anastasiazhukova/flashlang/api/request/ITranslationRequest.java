package com.github.anastasiazhukova.flashlang.api.request;

import com.github.anastasiazhukova.lib.contracts.IRequest;

public interface ITranslationRequest extends IRequest {

    String getSourceText();

    String getSourceLanguage();

    String getTargetLanguage();

    String getApiKey();

}
