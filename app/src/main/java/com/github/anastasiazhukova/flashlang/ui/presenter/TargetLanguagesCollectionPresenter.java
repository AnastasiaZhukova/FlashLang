package com.github.anastasiazhukova.flashlang.ui.presenter;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.UserManager;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.operations.Operations;
import com.github.anastasiazhukova.flashlang.ui.contract.TargetLanguagesCollectionContract;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.command.Command;

public class TargetLanguagesCollectionPresenter implements TargetLanguagesCollectionContract.Presenter {

    private TargetLanguagesCollectionContract.View mView;

    @Override
    public void attachView(final TargetLanguagesCollectionContract.View pView) {
        mView = pView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void load() {
        final String sourceLanguageKey = mView.getSourceLanguageKey();
        final String currentUserId = UserManager.getCurrentUser().getId();
        final Collection.ByOwnerIdSelector byOwnerIdSelector = new Collection.ByOwnerIdSelector(currentUserId);
        final Collection.BySourceLanguageSelector bySourceLanguageSelector = new Collection.BySourceLanguageSelector(sourceLanguageKey);
        final IOperation<Cursor> loadTargetLanguagesCursor = Operations.newOperation()
                .info()
                .local()
                .collection()
                .loadCursor(null, byOwnerIdSelector, bySourceLanguageSelector);
        final ICallback<Cursor> callback = new ICallback<Cursor>() {

            @Override
            public void onSuccess(final Cursor pCursor) {
                publishResult(pCursor);
            }

            @Override
            public void onError(final Throwable pThrowable) {
                publishError(pThrowable);
            }
        };
        final Command<Cursor> command=new Command<>(loadTargetLanguagesCursor);
        command.setCallback(callback);

        IThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.ASYNC_TASK)
                .execute(command);

    }

    private void publishResult(final Cursor pCursor) {
        if (mView != null) {
            mView.onLoaded(pCursor);
        }
    }

    private void publishError(final Throwable pThrowable) {
        if (mView != null) {
            mView.onError(pThrowable.getMessage());
        }

    }

}
