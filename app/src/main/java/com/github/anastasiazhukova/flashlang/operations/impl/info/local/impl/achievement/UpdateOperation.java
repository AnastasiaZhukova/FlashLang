package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractUpdateOperation;

public class UpdateOperation extends AbstractUpdateOperation<Achievement> {

    public UpdateOperation(final Achievement pModel, final Selector[] pSelectors) {
        super(pModel, pSelectors);
    }
}
