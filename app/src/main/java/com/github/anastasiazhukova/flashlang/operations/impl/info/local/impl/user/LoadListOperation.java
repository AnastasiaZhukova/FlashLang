package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user;

import com.github.anastasiazhukova.flashlang.db.connector.ICursorConverter;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadListOperation;

public class LoadListOperation extends AbstractLoadListOperation<User> {

    public LoadListOperation(final String pGroupBy, final Selector[] pSelectors) {
        super(pGroupBy, pSelectors);
    }

    @Override
    protected String getTableName() {
        return User.DbKeys.TABLE_NAME;
    }

    @Override
    protected ICursorConverter<User> getCursorConverter() {
        return new User.CursorConverter();
    }
}
