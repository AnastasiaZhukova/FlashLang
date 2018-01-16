package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractLoadQueryOperation;

public class LoadQueryOperation extends AbstractLoadQueryOperation<Achievement> {

    public LoadQueryOperation(final Selector pSelector) {
        super(pSelector);
    }

    @Override
    protected String getTableName() {
        return Achievement.DbKeys.TABLE_NAME;
    }
}
