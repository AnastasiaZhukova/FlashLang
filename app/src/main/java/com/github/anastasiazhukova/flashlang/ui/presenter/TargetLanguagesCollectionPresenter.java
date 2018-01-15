package com.github.anastasiazhukova.flashlang.ui.presenter;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.UserManager;
import com.github.anastasiazhukova.flashlang.operations.LoadTargetLanguagesCollectionOperation;
import com.github.anastasiazhukova.flashlang.ui.contract.TargetLanguagesCollectionContract;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;

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
        final IOperation<Cursor> loadOperation = new LoadTargetLanguagesCollectionOperation(currentUserId, sourceLanguageKey);
        ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.ASYNC_TASK)
                .execute(loadOperation, new ICallback<Cursor>() {

                    @Override
                    public void onSuccess(final Cursor pCursor) {
                        publishResult(pCursor);
                    }

                    @Override
                    public void onError(final Throwable pThrowable) {
                        publishError(pThrowable);
                    }
                });

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
