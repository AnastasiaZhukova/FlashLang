package com.github.anastasiazhukova.lib.db;

public interface IDb {

    String getName();

    int getVersion();

    Class<?>[] getTableModels();
}
