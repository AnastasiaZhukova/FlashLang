package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.utils.OperationUtils;
import com.github.anastasiazhukova.lib.contracts.IOperation;

import java.util.List;

public class LoadCardsListOperation implements IOperation<List<Card>> {

    private final String mUserId;
    private final String mSourceLanguageKey;
    private final String mTargetLanguageKey;

    public LoadCardsListOperation(final String pUserId, final String pSourceLanguageKey, final String pTargetLanguageKey) {
        mUserId = pUserId;
        mSourceLanguageKey = pSourceLanguageKey;
        mTargetLanguageKey = pTargetLanguageKey;
    }

    @Override
    public List<Card> perform() throws Exception {
        final String collectionId = OperationUtils.getCollectionId(mUserId, mSourceLanguageKey, mTargetLanguageKey);
        if (collectionId != null) {
            List<Card> cards = IDbTableConnector.Companion.getInstance().get(Card.DbKeys.TABLE_NAME, new Card.CursorConverter(),
                    null,
                    new Collection.ByOwnerIdSelector(collectionId));
            return cards;
        } else {
            throw new IllegalStateException("Can't find cards");
        }
    }
}
