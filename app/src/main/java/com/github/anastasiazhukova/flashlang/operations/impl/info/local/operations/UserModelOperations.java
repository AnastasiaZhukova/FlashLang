package com.github.anastasiazhukova.flashlang.operations.impl.info.local.operations;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.IModelOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user.DeleteOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user.LoadCursorOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user.LoadListOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user.LoadSingleOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user.UpdateOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user.UploadAllOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.user.UploadOperation;
import com.github.anastasiazhukova.lib.contracts.IOperation;

import java.util.List;

public class UserModelOperations implements IModelOperations<User> {

    @Override
    public IOperation<Boolean> upload(final User pUser) {
        return new UploadOperation(pUser);
    }

    @Override
    public IOperation<Boolean> uploadAll(final User[] pModels) {
        return new UploadAllOperation(pModels);
    }

    @Override
    public IOperation<User> loadSingle(final Selector... pSelectors) {
        return new LoadSingleOperation(pSelectors);
    }

    @Override
    public IOperation<List<User>> loadList(final String pGroupBy, final Selector... pSelectors) {
        return new LoadListOperation(pGroupBy, pSelectors);
    }

    @Override
    public IOperation<Cursor> loadCursor(final String pGroupBy, final Selector... pSelectors) {
        return new LoadCursorOperation(pGroupBy, pSelectors);
    }

    @Override
    public IOperation<Boolean> update(final User pUser, final Selector... pSelectors) {
        return new UpdateOperation(pUser, pSelectors);
    }

    @Override
    public IOperation<Boolean> delete(final Selector... pSelectors) {
        return new DeleteOperation(pSelectors);
    }
}
