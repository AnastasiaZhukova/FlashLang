package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.UserManager;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;

import java.util.List;

public class IncreaseUserAchivementOperation implements IOperation<Void> {

    private final String mUserId;
    private final int mConnectionsCount;
    private final int mWordsCount;

    public IncreaseUserAchivementOperation(final String pUserId, final int pConnectionsCount, final int pWordsCount) {
        mUserId = pUserId;
        mConnectionsCount = pConnectionsCount;
        mWordsCount = pWordsCount;
    }

    @Override
    public Void perform() throws Exception {
        final List<User> users = IDbTableConnector.Companion.getInstance().get(User.DbKeys.TABLE_NAME, new User.CursorConverter(), null, new User.ByIdSelector(mUserId));
        if (users != null && !users.isEmpty()) {
            final User user = users.get(0);
            user.increaseConnectionCount(mConnectionsCount);
            user.increaseWordCount(mWordsCount);
            updateUserInDb(user);
            updateCurrentUser(user);
        }
        return null;
    }

    private void updateUserInDb(final User pUser) {
        final IOperation updateUserOperation = new UpdateUserOperation(pUser);
        ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD)
                .execute(updateUserOperation);
    }

    private void updateCurrentUser(final User pUser) {
        UserManager.setCurrentUser(pUser);
    }
}
