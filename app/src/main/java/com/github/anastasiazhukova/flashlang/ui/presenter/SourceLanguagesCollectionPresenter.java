package com.github.anastasiazhukova.flashlang.ui.presenter;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.operations.LoadSourceLanguagesCollectionOperation;
import com.github.anastasiazhukova.flashlang.ui.contract.SourceLanguagesCollectionContract;
import com.github.anastasiazhukova.flashlang.utils.UserUtils;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;

public class SourceLanguagesCollectionPresenter implements SourceLanguagesCollectionContract.Presenter {

    private SourceLanguagesCollectionContract.View mView;

    @Override
    public void attachView(final SourceLanguagesCollectionContract.View pView) {
        mView = pView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void load() {
        final String currentUserId = UserUtils.getCurrentUserId();
        final IOperation<Cursor> loadOperation = new LoadSourceLanguagesCollectionOperation(currentUserId);
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


