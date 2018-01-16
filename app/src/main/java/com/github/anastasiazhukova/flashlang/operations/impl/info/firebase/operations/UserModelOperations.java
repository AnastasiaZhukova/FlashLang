package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.operations;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.IModelOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user.DeleteOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user.LoadListOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user.LoadQueryOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user.LoadSingleOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user.UpdateOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user.UploadAllOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.user.UploadOperation;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.google.firebase.database.Query;

import java.util.List;

public class UserModelOperations implements IModelOperations<User> {

    @Override
    public IOperation<Void> upload(final User pModel) {
        return new UploadOperation(pModel);
    }

    @Override
    public IOperation<Void> uploadAll(final User[] pModels) {
        return new UploadAllOperation(pModels);
    }

    @Override
    public IOperation<Void> loadList(final ICallback<List<User>> pCallback, final Selector pSelector) {
        return new LoadListOperation(pCallback, pSelector);
    }

    @Override
    public IOperation<Void> loadSingle(final ICallback<User> pCallback, final Selector pSelector) {
        return new LoadSingleOperation(pCallback, pSelector);
    }

    @Override
    public IOperation<Query> loadQuery(final Selector pSelector) {
        return new LoadQueryOperation(pSelector);
    }

    @Override
    public IOperation<Void> update(final User pModel, final Selector pSelector) {
        return new UpdateOperation(pModel, pSelector);
    }

    @Override
    public IOperation<Void> delete(final Selector pSelector) {
        return new DeleteOperation(pSelector);
    }
}
