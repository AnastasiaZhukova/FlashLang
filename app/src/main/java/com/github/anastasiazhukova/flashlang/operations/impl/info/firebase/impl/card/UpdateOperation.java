package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.card;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractUpdateOperation;

public class UpdateOperation extends AbstractUpdateOperation<Card> {

    public UpdateOperation(final Card pModel, final Selector pSelector) {
        super(pModel, pSelector);
    }
}
