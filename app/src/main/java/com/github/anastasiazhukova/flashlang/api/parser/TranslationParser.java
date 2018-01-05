package com.github.anastasiazhukova.flashlang.api.parser;

import com.github.anastasiazhukova.flashlang.api.response.ITranslationResponse;
import com.github.anastasiazhukova.lib.contracts.IResponseConverter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TranslationParser implements IResponseConverter<ITranslationResponse[], InputStream> {

    @Override
    public ITranslationResponse[] convert(final InputStream pInputStream) throws IOException {
        //TODO stream is not closed
        final Reader reader = new InputStreamReader(pInputStream);
        final ResponseModel responseModel = new Gson().fromJson(reader, ResponseModel.class);
        if (responseModel != null) {

            if (responseModel.getError() != null) {
                return null;
            }
            final ResponseModel.Translations translations = responseModel.getTranslations();
            if (translations != null && translations.getElements().length > 0) {
                return translations.getElements();
            }
        }
        return null;
    }

}
