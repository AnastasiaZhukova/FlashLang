package com.github.anastasiazhukova.flashlang.ui;

public interface ViewConstants {

    final class Splash {

        public static final int ANIMATION_DURATION_MILLIS = 2000;
    }

    final class Auth {

        public static final int SIGN_IN_POSITION = 0;
        public static final int SIGN_UP_POSITION = 1;
        public static final int FRAGMENT_COUNT = 2;
    }

    final class Collection {

        public static final String SOURCE_COLLECTION_FRAGMENT_TAG = "sourcecollection";
        public static final String TARGET_COLLECTION_FRAGMENT_TAG = "targetcollection";
        public static final String CARDS_COLLECTION_FRAGMENT_TAG = "cardscollection";
    }

    final class TargetCollection {

        public static final String SOURCE_LANGUAGE_KEY = "sourcelanguage";
    }

    final class CardsCollection {

        public static final String COLLECTION_ID_KEY = "collectionid";
    }

}
