package com.github.anastasiazhukova.lib.threadingTest;

import com.github.anastasiazhukova.lib.contracts.IOperation;

public class TestOperation implements IOperation<String> {

    @Override
    public String perform() {
        return "Success";
    }

}
