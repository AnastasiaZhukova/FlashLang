package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.operations;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.IModelOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement.DeleteOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement.LoadListOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement.LoadQueryOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement.LoadSingleOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement.UpdateOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement.UploadAllOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.achievement.UploadOperation;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.google.firebase.database.Query;

import java.util.List;

public class AchievementModelOperations implements IModelOperations<Achievement> {

    @Override
    public IOperation<Void> upload(final Achievement pModel) {
        return new UploadOperation(pModel);
    }

    @Override
    public IOperation<Void> uploadAll(final Achievement[] pModels) {
        return new UploadAllOperation(pModels);
    }

    @Override
    public IOperation<Void> loadList(final ICallback<List<Achievement>> pCallback, final Selector pSelector) {
        return new LoadListOperation(pCallback, pSelector);
    }

    @Override
    public IOperation<Void> loadSingle(final ICallback<Achievement> pCallback, final Selector pSelector) {
        return new LoadSingleOperation(pCallback, pSelector);
    }

    @Override
    public IOperation<Query> loadQuery(final Selector pSelector) {
        return new LoadQueryOperation(pSelector);
    }

    @Override
    public IOperation<Void> update(final Achievement pModel, final Selector pSelector) {
        return new UpdateOperation(pModel, pSelector);
    }

    @Override
    public IOperation<Void> delete(final Selector pSelector) {
        return new DeleteOperation(pSelector);
    }
}
