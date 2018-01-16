package com.github.anastasiazhukova.flashlang.operations.impl.common.impl.fetch;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.Operations;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.command.Command;
import com.github.anastasiazhukova.lib.threading.command.ICommand;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

public class FetchUserInfoOperation implements IOperation<Void> {

    private final String mUserId;
    private final IExecutor mExecutor;

    public FetchUserInfoOperation(final String pUserId) {
        mUserId = pUserId;
        mExecutor = IThreadingManager.Imlp.getThreadingManager()
                .getExecutor(ExecutorType.THREAD);
    }

    @Override
    public Void perform() throws Exception {
        final ICallback<User> callback = new ICallback<User>() {

            @Override
            public void onSuccess(final User pUser) {
                uploadToDb(pUser);
            }

            @Override
            public void onError(final Throwable pThrowable) {

            }
        };
        final IOperation<Void> loadFromFirebase = Operations.newOperation()
                .info()
                .firebase()
                .user()
                .loadSingle(callback, new User.ByIdSelector(mUserId));
        final ICommand command = new Command<>(loadFromFirebase);
        mExecutor.execute(command);
        return null;
    }

    private void uploadToDb(final User pUser) {
        final IOperation<Boolean> uploadToLocalDb = Operations.newOperation()
                .info()
                .local()
                .user()
                .upload(pUser);
        final ICommand command = new Command<>(uploadToLocalDb);
        mExecutor.execute(command);
    }
}
