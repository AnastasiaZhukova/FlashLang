package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.card.ICard;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IFirebaseDbConnector;
import com.github.anastasiazhukova.flashlang.utils.OperationUtils;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

public class DeleteCardOperation implements IOperation<Void> {

    private final ICard mCard;
    private final IExecutor mExecutor;

    public DeleteCardOperation(final ICard pCard) {
        mCard = pCard;
        mExecutor = ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.EXECUTOR_SERVICE);
    }

    @Override
    public Void perform() throws Exception {
        final String collectionId = mCard.getOwnerId();
        removeCard(mCard);
        if (OperationUtils.isCollectionEmpty(collectionId)) {
            removeCollection(collectionId);
        }
        return null;
    }

    private void removeCard(final ICard pCard) {
        final Selector selector = new Card.ByIdSelector(pCard.getId());
        IDbTableConnector.Companion.getInstance().delete(Card.DbKeys.TABLE_NAME, selector);
        IFirebaseDbConnector.Impl.Companion.getInstance().delete(Card.DbKeys.TABLE_NAME, selector);
    }

    private void removeCollection(final String pCollectionId) {
        final IOperation operation = new DeleteCollectionOperation(pCollectionId);
        mExecutor.execute(operation);
    }
}
