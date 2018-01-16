package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement;

import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadListOperation;

public class LoadListOperation extends AbstractLoadListOperation<Achievement> {

    public LoadListOperation(final String pGroupBy, final Selector[] pSelectors) {
        super(pGroupBy, pSelectors);
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
