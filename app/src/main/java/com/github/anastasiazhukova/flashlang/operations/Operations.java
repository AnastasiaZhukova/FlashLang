package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.operations.impl.auth.AuthOperationBuilder;
import com.github.anastasiazhukova.flashlang.operations.impl.common.CommonOperationsBuilder;
import com.github.anastasiazhukova.flashlang.operations.impl.info.InfoOperationsBuilder;
import com.github.anastasiazhukova.flashlang.operations.impl.info.storage.StorageOperationsBuilder;
import com.github.anastasiazhukova.flashlang.operations.impl.translate.TranslateOperationBuilder;

public class Operations {

    public static Operations newOperation() {
        return new Operations();
    }

    public AuthOperationBuilder auth() {
        return new AuthOperationBuilder();
    }

    public CommonOperationsBuilder common() {
        return new CommonOperationsBuilder();
    }

    public TranslateOperationBuilder translate() {
        return new TranslateOperationBuilder();
    }

    public InfoOperationsBuilder info() {
        return new InfoOperationsBuilder();
    }

    public StorageOperationsBuilder storage() {
        return new StorageOperationsBuilder();
    }

}
