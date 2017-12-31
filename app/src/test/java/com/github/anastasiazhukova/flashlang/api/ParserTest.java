package com.github.anastasiazhukova.flashlang.api;

import com.github.anastasiazhukova.flashlang.api.response.ITranslationResponse;
import com.github.anastasiazhukova.flashlang.api.response.TranslationParser;
import com.github.anastasiazhukova.flashlang.mocks.TranslatedObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParserTest {

    private static final int EXAMPLES_INDEX = 13;
    private static final int TRANSLATE_INDEX = 0;
    private static final int SOURCE_LANGUAGE_INDEX = 2;
    private InputStream mFirstResource;
    private InputStream mSecondResource;
    private InputStream mThirdResource;

    @Before
    public void setUp() throws Exception {
        mFirstResource = this.getClass().getClassLoader().getResourceAsStream("first_translate_sample.json");
        mSecondResource = this.getClass().getClassLoader().getResourceAsStream("second_translate_sample.json");
        mThirdResource = this.getClass().getClassLoader().getResourceAsStream("third_translate_sample.json");
    }

    @Test
    public void testParse() throws IOException {

        final TranslatedObject firstObject = parse(mFirstResource);
        final TranslatedObject secondObject = parse(mSecondResource);
        final TranslatedObject thirdObject = parse(mThirdResource);

        assertEquals("cover", firstObject.getSourceText());
        assertEquals("обложка", firstObject.getTranslation());
        assertEquals(30, firstObject.getExamples().length);
        assertEquals("en", firstObject.getSourceLanguage());

        assertEquals("try", secondObject.getSourceText());
        assertEquals("пытаться", secondObject.getTranslation());
        assertNull(null, secondObject.getExamples());
        assertEquals("en", secondObject.getSourceLanguage());

        assertEquals("обложка", thirdObject.getSourceText());
        assertEquals("cover", thirdObject.getTranslation());
        assertNull(null, thirdObject.getExamples());
        assertEquals("ru", thirdObject.getSourceLanguage());
    }

    @Test
    public void testTranslationParser() throws IOException {

        final TranslationParser translationParser = new TranslationParser();
        final ITranslationResponse firstObject = translationParser.convert(mFirstResource);
        final ITranslationResponse secondObject = translationParser.convert(mSecondResource);
        final ITranslationResponse thirdObject = translationParser.convert(mThirdResource);

        assertEquals("обложка", firstObject.getTranslatedText());
        assertEquals(30, firstObject.getExamples().size());
        assertEquals("en", firstObject.getSourceLanguageKey());

        assertEquals("пытаться", secondObject.getTranslatedText());
        assertNull(null, secondObject.getExamples());
        assertEquals("en", secondObject.getSourceLanguageKey());

        assertEquals("cover", thirdObject.getTranslatedText());
        assertNull(null, thirdObject.getExamples());
        assertEquals("ru", thirdObject.getSourceLanguageKey());


    }

    @After
    public void tearDown() throws Exception {
        try {
            mFirstResource.close();
            mSecondResource.close();
            mThirdResource.close();

        } catch (final IOException ignored) {
        }
    }

    private TranslatedObject parse(final InputStream pInputStream) {

        String sourceText = null;
        String sourceLanguage = null;
        String translationString = null;
        String[] examples = null;

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
                final JsonArray translationArray = (JsonArray) translationElement;
                if (translationArray.size() > 0) {
                    final JsonElement element = translationArray.get(0);
                    if (element.isJsonArray()) {
                        final JsonArray wordsElement = (JsonArray) element;
                        translationString = wordsElement.get(0).getAsJsonPrimitive().getAsString();
                        sourceText = wordsElement.get(1).getAsJsonPrimitive().getAsString();

                    }

                }
            }

            if (examplesElement != null && examplesElement.isJsonArray()) {
                final JsonArray baseExamplesArray = (JsonArray) examplesElement;
                if (baseExamplesArray.size() > 0) {
                    final JsonArray examplesArray = (JsonArray) baseExamplesArray.get(0);
                    if (examplesArray.size() > 0) {
                        examples = new String[examplesArray.size()];
                        for (int i = 0; i < examplesArray.size(); i++) {
                            examples[i] = examplesArray.get(i).getAsJsonArray().get(0).getAsString();
                        }

                    }
                }
            }

        }

        final TranslatedObject translatedObject = new TranslatedObject();
        translatedObject.setSourceLanguage(sourceLanguage);
        translatedObject.setTranslation(translationString);
        translatedObject.setExamples(examples);
        translatedObject.setSourceText(sourceText);

        return translatedObject;

    }

}
