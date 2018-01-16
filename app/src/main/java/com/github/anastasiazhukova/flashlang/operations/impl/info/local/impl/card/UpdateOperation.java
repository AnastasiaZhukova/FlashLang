package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.card;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractUpdateOperation;

public class UpdateOperation extends AbstractUpdateOperation<Card> {

    public UpdateOperation(final Card pModel, final Selector[] pSelectors) {
        super(pModel, pSelectors);
    }
}
