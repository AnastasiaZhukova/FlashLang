package com.github.anastasiazhukova.flashlang.ui.presenter;

import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.firebase.auth.FirebaseUserManager;
import com.github.anastasiazhukova.flashlang.operations.LoadCardsListOperation;
import com.github.anastasiazhukova.flashlang.ui.contract.GameContract;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;

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
            final String currentUser = FirebaseUserManager.Impl.Companion.getInstance().getCurrentUser().getUid();
            //todo final String currentUser = UserManager.getCurrentUser().getId();
            final String sourceLanguageKey = mView.getSourceLanguageKey();
            final String targetLanguageKey = mView.getTargetLanguageKey();
            final IOperation<List<Card>> operation = new LoadCardsListOperation(currentUser,
                    sourceLanguageKey, targetLanguageKey);
            ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.ASYNC_TASK)
                    .execute(operation, new ICallback<List<Card>>() {

                        @Override
                        public void onSuccess(final List<Card> pCards) {
                            mView.onLoaded(pCards);
                        }

                        @Override
                        public void onError(final Throwable pThrowable) {
                            mView.onError(pThrowable.getMessage());
                        }
                    });
        }

    }

}
