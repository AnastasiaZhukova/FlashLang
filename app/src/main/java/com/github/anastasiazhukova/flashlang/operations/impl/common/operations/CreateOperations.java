package com.github.anastasiazhukova.flashlang.operations.impl.common.operations;

import com.github.anastasiazhukova.flashlang.operations.impl.common.impl.CreateCollectionOperation;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class CreateOperations {

    public IOperation<String> collection(final String pUserId, final String pSourceLanguageKey, final String pTargetLanguageKey) {
        return new CreateCollectionOperation(pUserId, pSourceLanguageKey, pTargetLanguageKey);
    }

}
