package com.github.anastasiazhukova.utilslib.httpclient;

import android.support.annotation.NonNull;

import com.github.anastasiazhukova.utilslib.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpClient implements IHttpClient {

    @Override
    public HttpResponse getResponse(final IHttpRequest pRequest) throws Exception {
        final HttpResponse response = new HttpResponse();
        response.setRequest(pRequest);

        HttpURLConnection connection = null;
        InputStream stream = null;
        try {

            connection = buildConnection(pRequest);
            final boolean isSuccessful = isSuccessful(connection);

            stream = getInputStream(connection);

            final String readStream = readStream(stream);
            if (!isSuccessful) {
                response.setError(new Exception(readStream));
            } else {
                response.setResponse(readStream);
            }
        } finally {
            IOUtils.close(stream);
            IOUtils.disconnect(connection);
        }

        return response;
    }

    @Override
    public <Response> Response getResponse(final IHttpRequest pRequest, final ResponseConverter<Response> pResponseConverter) throws IOException {

        HttpURLConnection connection = null;
        InputStream stream = null;
        try {
            connection = buildConnection(pRequest);
            stream = getInputStream(connection);
            return pResponseConverter.convert(stream);
        } finally {
            IOUtils.close(stream);
            IOUtils.disconnect(connection);
        }
    }

    @Override
    public void handle(final IHttpRequest pRequest) throws Exception {
        getResponse(pRequest);
    }

    private InputStream getInputStream(final HttpURLConnection pConnection) throws IOException {
        final InputStream stream;
        if (isSuccessful(pConnection)) {
            stream = pConnection.getInputStream();
        } else {
            stream = pConnection.getErrorStream();
        }
        return stream;
    }

    @NonNull
    private HttpURLConnection buildConnection(final IHttpRequest pRequest) throws IOException {
        final HttpURLConnection connection;
        final URL requestUrl = pRequest.getUrl();
        connection = (HttpURLConnection) requestUrl.openConnection();

        connection.setRequestMethod(pRequest.getMethod());

        final Headers headers = pRequest.getHeaders();
        if (headers != null) {
            final Map<String, String> map = headers.getMap();
            for (final String key : map.keySet()) {
                connection.addRequestProperty(key, map.get(key));
            }
        }
        return connection;
    }

    private static final int SUCCESS_CODE_START = 200;
    private static final int SUCCESS_CODE_END = 300;

    private boolean isSuccessful(final HttpURLConnection pConnection) throws IOException {
        return pConnection.getResponseCode() >= SUCCESS_CODE_START && pConnection.getResponseCode() < SUCCESS_CODE_END;
    }

    @NonNull
    private String readStream(final InputStream pStream) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(pStream));
        final StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

}
