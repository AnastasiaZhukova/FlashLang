package com.github.anastasiazhukova.flashlang.operations.impl.common.operations;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.impl.common.impl.LoadCurrentUserOperation;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class LoadOperations {

    public IOperation<User> currentUser(){
        return new LoadCurrentUserOperation();
    }

}
