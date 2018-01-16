package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement;

import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadSingleOperation;

public class LoadSingleOperation extends AbstractLoadSingleOperation<Achievement> {

    public LoadSingleOperation(final Selector[] pSelectors) {
        super(pSelectors);
    }

    @Override
    protected String getTableName() {
        return Achievement.DbKeys.TABLE_NAME;
    }

    @Override
    protected ICursorConverter<Achievement> getCursorConverter() {
        return new Achievement.CursorConverter();
    }
}
