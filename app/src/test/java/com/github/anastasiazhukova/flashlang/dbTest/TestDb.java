package com.github.anastasiazhukova.flashlang.dbTest;

import com.github.anastasiazhukova.lib.db.IDb;

public class TestDb implements IDb {

    @Override
    public String getName() {
        return "test.db";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public Class<?>[] getTableModels() {
        return new Class[]{
                TestModel.class
        };
    }
}
