package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.collection;

import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractUploadOperation;

public class UploadOperation extends AbstractUploadOperation<Collection> {

    public UploadOperation(final Collection pModel) {
        super(pModel);
    }
}
