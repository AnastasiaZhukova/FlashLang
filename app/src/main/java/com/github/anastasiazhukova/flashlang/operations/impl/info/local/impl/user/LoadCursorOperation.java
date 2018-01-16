package com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.generic.AbstractLoadCursorOperation;

public class LoadCursorOperation extends AbstractLoadCursorOperation<User> {

    public LoadCursorOperation(final String pGroupBy, final Selector[] pSelectors) {
        super(pGroupBy, pSelectors);
    }

    @Override
    protected String getTableName() {
        return User.DbKeys.TABLE_NAME;
    }
}
