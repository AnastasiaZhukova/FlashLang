package com.github.anastasiazhukova.flashlang.operations.impl.common.operations;

import com.github.anastasiazhukova.flashlang.operations.impl.common.impl.IncreaseUserAchievementOperation;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class UpdateOperations {

    public IOperation<Void> increaseUserAchievement(final String pUserId, final int pConnectionsIncrement, final int pWordsIncrement) {
        return new IncreaseUserAchievementOperation(pUserId, pConnectionsIncrement, pWordsIncrement);
    }

}
