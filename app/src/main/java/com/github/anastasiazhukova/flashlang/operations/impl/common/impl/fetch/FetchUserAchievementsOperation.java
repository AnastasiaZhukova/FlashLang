package com.github.anastasiazhukova.flashlang.operations.impl.common.impl.fetch;

import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.operations.Operations;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.command.Command;
import com.github.anastasiazhukova.lib.threading.command.ICommand;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

import java.util.Collection;
import java.util.List;

public class FetchUserAchievementsOperation implements IOperation<Void> {

    private final String mUserId;
    private final IExecutor mExecutor;

    public FetchUserAchievementsOperation(final String pUserId) {
        mUserId = pUserId;
        mExecutor = IThreadingManager.Imlp.getThreadingManager()
                .getExecutor(ExecutorType.THREAD);
    }

    @Override
    public Void perform() throws Exception {
        final ICallback<List<Achievement>> callback = new ICallback<List<Achievement>>() {

            @Override
            public void onSuccess(final List<Achievement> pAchievements) {
                uploadToDb(pAchievements);
            }

            @Override
            public void onError(final Throwable pThrowable) {
            }
        };
        final IOperation<Void> loadFromFirebase = Operations.newOperation()
                .info()
                .firebase()
                .achievement()
                .loadList(callback, new Achievement.ByOwnerIdSelector(mUserId));
        final ICommand command = new Command<>(loadFromFirebase);
        mExecutor.execute(command);
        return null;
    }

    private void uploadToDb(final Collection<Achievement> pAchievements) {
        final Achievement[] achievements = new Achievement[pAchievements.size()];
        pAchievements.toArray(achievements);
        final IOperation<Boolean> uploadToLocalDb = Operations.newOperation()
                .info()
                .local()
                .achievement()
                .uploadAll(achievements);
        final ICommand command = new Command<>(uploadToLocalDb);
        mExecutor.execute(command);

    }
}
