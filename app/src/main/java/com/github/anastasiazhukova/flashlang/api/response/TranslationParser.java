package com.github.anastasiazhukova.flashlang.api.response;

import com.github.anastasiazhukova.lib.contracts.IResponseConverter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class TranslationParser implements IResponseConverter<ITranslationResponse> {

    private static final int EXAMPLES_INDEX = 13;
    private static final int TRANSLATE_INDEX = 0;
    private static final int SOURCE_LANGUAGE_INDEX = 2;
    private static final int EXAMPLE_STRING_INDEX = 0;

    @Override
    public ITranslationResponse convert(final InputStream pInputStream) throws IOException {

        String sourceLanguage = null;
        String translationString = null;
        List<String> examples = null;

        final Reader reader = new InputStreamReader(pInputStream);

        final JsonParser parser = new JsonParser();
        final JsonElement baseElement = parser.parse(reader);

        if (baseElement.isJsonArray()) {
            final JsonArray baseArray = (JsonArray) baseElement;

            JsonElement translationElement = null;
            JsonElement examplesElement = null;
            if (baseArray.size() > 0) {
                translationElement = baseArray.get(TRANSLATE_INDEX);

                if (baseArray.size() > SOURCE_LANGUAGE_INDEX) {
                    sourceLanguage = baseArray.get(SOURCE_LANGUAGE_INDEX).getAsString();
                }

                if (baseArray.size() > EXAMPLES_INDEX) {
                    examplesElement = baseArray.get(EXAMPLES_INDEX);
                }
            }

            if (translationElement != null && translationElement.isJsonArray()) {
                translationString = extractTranslatedString((JsonArray) translationElement);
            }

            if (examplesElement != null && examplesElement.isJsonArray()) {
                examples = extractExamples((JsonArray) examplesElement);
            }

        }

        final TranslationResponse translatedObject = new TranslationResponse();
        translatedObject.setSourceLanguage(sourceLanguage);
        translatedObject.setTranslatedText(translationString);
        translatedObject.setExamples(examples);

        return translatedObject;
    }

    private List<String> extractExamples(final JsonArray pExamplesElement) {
        List<String> pExamples = null;
        if (pExamplesElement.size() > 0) {
            final JsonArray examplesArray = (JsonArray) pExamplesElement.get(0);
            if (examplesArray.size() > 0) {
                pExamples = new ArrayList<>(examplesArray.size());
                for (int i = 0; i < examplesArray.size(); i++) {
                    pExamples.add(examplesArray.get(i).getAsJsonArray().get(EXAMPLE_STRING_INDEX).getAsString());
                }
            }
        }
        return pExamples;
    }

    private String extractTranslatedString(final JsonArray pTranslationElement) {
        String pTranslationString = null;
        if (pTranslationElement.size() > 0) {
            final JsonElement element = pTranslationElement.get(0);
            if (element.isJsonArray()) {
                final JsonArray wordsElement = (JsonArray) element;
                pTranslationString = wordsElement.get(0).getAsJsonPrimitive().getAsString();
            }

        }
        return pTranslationString;
    }
}
