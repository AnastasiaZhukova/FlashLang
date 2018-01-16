package com.github.anastasiazhukova.flashlang.operations.impl.info.local.operations;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.IModelOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement.DeleteOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement.LoadCursorOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement.LoadListOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement.LoadSingleOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement.UpdateOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement.UploadAllOperation;
import com.github.anastasiazhukova.flashlang.operations.impl.info.local.impl.achievement.UploadOperation;
import com.github.anastasiazhukova.lib.contracts.IOperation;

import java.util.List;

public class AchievementModelOperations implements IModelOperations<Achievement> {

    @Override
    public IOperation<Boolean> upload(final Achievement pAchievement) {
        return new UploadOperation(pAchievement);
    }

    @Override
    public IOperation<Boolean> uploadAll(final Achievement[] pModels) {
        return new UploadAllOperation(pModels);
    }

    @Override
    public IOperation<Achievement> loadSingle(final Selector... pSelectors) {
        return new LoadSingleOperation(pSelectors);
    }

    @Override
    public IOperation<List<Achievement>> loadList(final String pGroupBy, final Selector... pSelectors) {
        return new LoadListOperation(pGroupBy, pSelectors);
    }

    @Override
    public IOperation<Cursor> loadCursor(final String pGroupBy, final Selector... pSelectors) {
        return new LoadCursorOperation(pGroupBy, pSelectors);
    }

    @Override
    public IOperation<Boolean> update(final Achievement pAchievement, final Selector... pSelectors) {
        return new UpdateOperation(pAchievement, pSelectors);
    }

    @Override
    public IOperation<Boolean> delete(final Selector... pSelectors) {
        return new DeleteOperation(pSelectors);
    }

}
