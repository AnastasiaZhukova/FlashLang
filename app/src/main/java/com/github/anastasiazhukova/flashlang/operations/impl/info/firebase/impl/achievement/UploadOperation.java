package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement;

import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractUploadOperation;

public class UploadOperation extends AbstractUploadOperation<Achievement> {

    public UploadOperation(final Achievement pModel) {
        super(pModel);
    }
}
