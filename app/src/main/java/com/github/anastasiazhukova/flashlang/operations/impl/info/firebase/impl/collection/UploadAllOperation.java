package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.collection;

import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractUploadAllOperation;

public class UploadAllOperation extends AbstractUploadAllOperation<Collection> {

    public UploadAllOperation(final Collection[] pModels) {
        super(pModels);
    }
}
