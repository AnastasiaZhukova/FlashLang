package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.card;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractDeleteOperation;

public class DeleteOperation extends AbstractDeleteOperation<Card> {

    public DeleteOperation(final Selector[] pSelectors) {
        super(pSelectors);
    }

    @Override
    protected String getTableName() {
        return Card.DbKeys.TABLE_NAME;
    }
}
