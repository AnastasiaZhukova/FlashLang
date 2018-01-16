package com.github.anastasiazhukova.flashlang.operations.impl.common;

import com.github.anastasiazhukova.flashlang.operations.impl.common.operations.CreateOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.common.operations.FetchOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.common.operations.LoadOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.common.operations.UpdateOperations;

public class CommonOperationsBuilder {

    public LoadOperations load() {
        return new LoadOperations();
    }

    public CreateOperations create() {
        return new CreateOperations();
    }

    public UpdateOperations update() {
        return new UpdateOperations();
    }

    public FetchOperations fetch() {
        return new FetchOperations();
    }

}
