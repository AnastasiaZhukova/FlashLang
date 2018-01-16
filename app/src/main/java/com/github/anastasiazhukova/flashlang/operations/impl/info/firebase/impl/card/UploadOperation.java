package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.card;

import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractUploadOperation;

public class UploadOperation extends AbstractUploadOperation<Card> {

    public UploadOperation(final Card pModel) {
        super(pModel);
    }
}
