package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.card;

import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.firebase.db.connector.IDataSnapshotConverter;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.impl.generic.AbstractLoadSingleOperation;
import com.github.anastasiazhukova.lib.contracts.ICallback;

public class LoadSingleOperation extends AbstractLoadSingleOperation<Card> {

    public LoadSingleOperation(final ICallback<Card> pCallback, final Selector pSelector) {
        super(pCallback, pSelector);
    }

    @Override
    protected String getTableName() {
        return Card.DbKeys.TABLE_NAME;
    }

    @Override
    protected IDataSnapshotConverter<Card> getDateSnapshotConverter() {
        return new Card.DataSnapshotConverter();
    }
}
