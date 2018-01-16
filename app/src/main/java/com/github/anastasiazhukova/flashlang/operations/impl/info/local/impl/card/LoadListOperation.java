package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.card;

import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadListOperation;

public class LoadListOperation extends AbstractLoadListOperation<Card> {

    public LoadListOperation(final String pGroupBy, final Selector[] pSelectors) {
        super(pGroupBy, pSelectors);
    }

    @Override
    protected String getTableName() {
        return Card.DbKeys.TABLE_NAME;
    }

    @Override
    protected ICursorConverter<Card> getCursorConverter() {
        return new Card.CursorConverter();
    }
}
