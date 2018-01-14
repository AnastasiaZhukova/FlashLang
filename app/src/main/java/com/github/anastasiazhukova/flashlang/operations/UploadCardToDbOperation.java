package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class UploadCardToDbOperation implements IOperation<Void> {

    private final Card mCard;

    public UploadCardToDbOperation(final Card pCard) {
        mCard = pCard;
    }

    @Override
    public Void perform() throws Exception {
        IDbTableConnector.Companion.getInstance()
                .insert(mCard);
        IFirebaseDbConnector.Impl.Companion.getInstance()
                .insert(mCard);
        return null;
    }
}
