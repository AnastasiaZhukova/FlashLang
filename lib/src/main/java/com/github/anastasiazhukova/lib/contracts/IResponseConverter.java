package com.github.anastasiazhukova.lib.contracts;

import java.io.IOException;
import java.io.InputStream;

public interface IResponseConverter<Response> {

    Response convert(InputStream pInputStream) throws IOException;
}
