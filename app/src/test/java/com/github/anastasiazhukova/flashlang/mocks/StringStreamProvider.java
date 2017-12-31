package com.github.anastasiazhukova.flashlang.mocks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringStreamProvider {

    public InputStream get(final String pSource) throws IOException {
        return new ByteArrayInputStream(pSource.getBytes());
    }
}
