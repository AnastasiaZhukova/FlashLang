package com.github.anastasiazhukova.flashlang.api.parser;

import com.github.anastasiazhukova.flashlang.api.response.TranslationResponse;
import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("data")
    private
    Translations mTranslations;

    @SerializedName("error")
    private Error mError;

    public Translations getTranslations() {
        return mTranslations;
    }

    public Error getError() {
        return mError;
    }

    class Translations {

        @SerializedName("translations")
        private TranslationResponse[] mElements;

        public TranslationResponse[] getElements() {
            return mElements;
        }
    }

    class Error {

        @SerializedName("code")
        private int mCode;

        @SerializedName("message")
        private String mMessage;

        public int getCode() {
            return mCode;
        }

        public String getMessage() {
            return mMessage;
        }
    }
}
