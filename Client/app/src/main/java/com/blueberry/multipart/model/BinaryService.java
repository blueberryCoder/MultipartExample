package com.blueberry.multipart.model;

import com.blueberry.multipart.entity.UpLoadResponse;

import java.io.File;
import java.io.InputStream;

/**
 * Created by blueberry on 7/7/2017.
 */

public class BinaryService {

    private BinaryRepository mBinaryRepository;

    private BinaryService() {
        mBinaryRepository = BinaryRepository.newInstance();
    }

    public static BinaryService newInstance() {
        return new BinaryService();
    }

    public void binaryUpload(File file, Callback callback) {
        mBinaryRepository.uploadBinary(file, RealMultipartService
                .ObserveAdapter.<UpLoadResponse>of(callback));
    }

    public void binaryUpload(InputStream inputStream, Callback callback) {
        mBinaryRepository.uploadBinary(inputStream, RealMultipartService
                .ObserveAdapter.<UpLoadResponse>of(callback));
    }
}
