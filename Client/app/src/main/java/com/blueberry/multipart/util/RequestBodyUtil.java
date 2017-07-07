package com.blueberry.multipart.util;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * Created by blueberry on 7/7/2017.
 */

public class RequestBodyUtil {

    public static RequestBody create(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public long contentLength() throws IOException {
                return inputStream.available();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source=null;
                try {
                    source = Okio.source(inputStream);
                    sink.writeAll(source);
                }finally {
                    if(source!=null){
                        source.close();
                    }
                }
            }
        };
    }
}
