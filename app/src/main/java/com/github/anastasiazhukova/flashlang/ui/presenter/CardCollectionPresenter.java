package com.github.anastasiazhukova.flashlang.ui.presenter;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.domain.models.card.ICard;
import com.github.anastasiazhukova.flashlang.operations.DeleteCardOperation;
import com.github.anastasiazhukova.flashlang.operations.LoadCardCollectionOperation;
import com.github.anastasiazhukova.flashlang.ui.contract.CardCollectionContract;
import com.github.anastasiazhukova.flashlang.utils.UserUtils;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;

public class CardCollectionPresenter implements CardCollectionContract.Presenter {

    private CardCollectionContract.View mView;

    @Override
    public void attachView(CardCollectionContract.View pView) {
        pView = mView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void load() {
        final String currentUserId = UserUtils.getCurrentUserId();
        final String sourceLanguageKey = mView.getSourceLanguageKey();
        final String targetLanguageKey = mView.getTargetLanguageKey();

        final IOperation<Cursor> loadOperation = new LoadCardCollectionOperation(currentUserId, sourceLanguageKey, targetLanguageKey);
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

    @Override
    public void removeCard(final ICard pCard) {
        final IOperation operation = new DeleteCardOperation(pCard);
        ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD)
                .execute(operation);
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
