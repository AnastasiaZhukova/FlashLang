package com.github.anastasiazhukova.lib.cache;

import java.io.IOException;

public interface ICache<Key, GetValue, PutValue> {

    GetValue get(Key pKey) throws IOException;

    void put(Key pKey, PutValue pValue) throws IOException;
}
