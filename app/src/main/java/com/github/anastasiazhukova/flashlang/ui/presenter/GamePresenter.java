package com.github.anastasiazhukova.flashlang.ui.presenter;

import com.github.anastasiazhukova.flashlang.UserManager;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.operations.Operations;
import com.github.anastasiazhukova.flashlang.ui.contract.GameContract;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.command.Command;

import java.util.List;

public class GamePresenter implements GameContract.Presenter {

    private GameContract.View mView;

    @Override
    public void attachView(final GameContract.View pView) {
        mView = pView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void load() {
        if (mView != null) {
            final String currentUserId = UserManager.getCurrentUser().getId();
            final String sourceLanguageKey = mView.getSourceLanguageKey();
            final String targetLanguageKey = mView.getTargetLanguageKey();

            final Card.ByOwnerIdSelector byOwnerIdSelector = new Card.ByOwnerIdSelector(currentUserId);
            final Card.BySourceLanguageSelector bySourceLanguageSelector = new Card.BySourceLanguageSelector(sourceLanguageKey);
            final Card.ByTargetLanguageSelector byTargetLanguageSelector = new Card.ByTargetLanguageSelector(targetLanguageKey);

            final IOperation<List<Card>> loadCardsList = Operations.newOperation()
                    .info()
                    .local()
                    .card()
                    .loadList(null, byOwnerIdSelector, bySourceLanguageSelector, byTargetLanguageSelector);

            final ICallback<List<Card>> callback = new ICallback<List<Card>>() {

                @Override
                public void onSuccess(final List<Card> pCards) {
                    mView.onLoaded(pCards);
                }

                @Override
                public void onError(final Throwable pThrowable) {
                    mView.onError(pThrowable.getMessage());
                }
            };

            final Command<List<Card>> command = new Command<>(loadCardsList);
            command.setCallback(callback);

            IThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.ASYNC_TASK)
                    .execute(command);
        }

    }

}
