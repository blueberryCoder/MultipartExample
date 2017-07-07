package com.blueberry.multipart.model;

import java.io.File;
import java.util.Collection;

/**
 * Created by blueberry on 7/6/2017.
 */

public interface MultipartService {

    void singlePart(File file, Callback callback);

    void singleRequestBody(File file, Callback callback);

    void multiFilePart(Collection<File> files, Callback callback);

    void multiFileRequestBody(Collection<File> files, Callback callback);

    void multiPart(File file, String str, Callback callback);

    void multiRequestBody(File file, String str, Callback callback);

}
