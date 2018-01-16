package com.github.anastasiazhukova.flashlang.ui.presenter;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.UserManager;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.Operations;
import com.github.anastasiazhukova.flashlang.ui.contract.CardCollectionContract;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.command.Command;
import com.github.anastasiazhukova.lib.threading.command.ICommand;

public class CardCollectionPresenter implements CardCollectionContract.Presenter {

    private CardCollectionContract.View mView;

    @Override
    public void attachView(final CardCollectionContract.View pView) {
        mView = pView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void load() {
        final String currentUserId = UserManager.getCurrentUser().getId();
        final String sourceLanguageKey = mView.getSourceLanguageKey();
        final String targetLanguageKey = mView.getTargetLanguageKey();

        final Card.ByOwnerIdSelector byOwnerIdSelector = new Card.ByOwnerIdSelector(currentUserId);
        final Card.BySourceLanguageSelector bySourceLanguageSelector=new Card.BySourceLanguageSelector(sourceLanguageKey);
        final Card.ByTargetLanguageSelector byTargetLanguageSelector=new Card.ByTargetLanguageSelector(targetLanguageKey);
        final IOperation<Cursor> getCursor = Operations.newOperation()
                .info()
                .local()
                .card()
                .loadCursor(null, byOwnerIdSelector, bySourceLanguageSelector, byTargetLanguageSelector);

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

        final Command<Cursor> command=new Command<>(getCursor);
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
