package com.github.anastasiazhukova.lib.cache;

import java.io.IOException;

public interface ICache<Key, GetValue, PutValue> {

    GetValue get(Key pKey);

    void put(Key pKey, PutValue pValue) throws IOException;
}
