package com.blueberry.multipart.model;

import com.blueberry.multipart.entity.UpLoadResponse;

import java.io.File;

import io.reactivex.Observer;

/**
 * Created by blueberry on 7/6/2017.
 */

public interface MultipartRepository {

    /**
     * 单个文件上传。
     *
     * @param file     文件
     * @param observer 观察者
     */
    void singleFileUpload(File file, Observer<UpLoadResponse> observer);

    /**
     * 多个文件上传。
     *
     * @param files 文件数组
     * @param observer 观察者
     */
    void multiFileUpload(File[] files, Observer<UpLoadResponse> observer);

    /**
     * 文件+文本上传
     * @param file
     * @param text
     * @param observer
     */
    void multiUpload(File file, String text, Observer<UpLoadResponse> observer);
}
