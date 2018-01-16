package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.card;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractLoadQueryOperation;

public class LoadQueryOperation extends AbstractLoadQueryOperation<Card> {

    public LoadQueryOperation(final Selector pSelector) {
        super(pSelector);
    }

    @Override
    protected String getTableName() {
        return Card.DbKeys.TABLE_NAME;
    }
}
