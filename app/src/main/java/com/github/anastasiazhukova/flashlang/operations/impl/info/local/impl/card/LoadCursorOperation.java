package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.card;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadCursorOperation;

public class LoadCursorOperation extends AbstractLoadCursorOperation<Card> {

    public LoadCursorOperation(final String pGroupBy, final Selector[] pSelectors) {
        super(pGroupBy, pSelectors);
    }

    @Override
    protected String getTableName() {
        return Card.DbKeys.TABLE_NAME;
    }
}
