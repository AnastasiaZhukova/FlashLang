package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.card;

import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractUploadAllOperation;

public class UploadAllOperation extends AbstractUploadAllOperation<Card> {

    public UploadAllOperation(final Card[] pModels) {
        super(pModels);
    }
}
