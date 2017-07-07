package com.blueberry.multipart.model;

import com.blueberry.multipart.api.MultipartApi;
import com.blueberry.multipart.entity.UpLoadResponse;
import com.blueberry.multipart.http.RetrofitHelper;
import com.blueberry.multipart.util.TransformUtil;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by blueberry on 7/6/2017.
 */

public class MultipartRepositoryRequestBodyImpl implements MultipartRepository {

    private MultipartApi mService;

    public static MultipartRepositoryRequestBodyImpl newInstance() {
        return new MultipartRepositoryRequestBodyImpl();
    }

    private MultipartRepositoryRequestBodyImpl() {
        mService = RetrofitHelper
                .getInstance()
                .getRetrofit()
                .create(MultipartApi.class);
    }

    @Override
    public void singleFileUpload(File file, Observer<UpLoadResponse> observer) {
        mService.singleRequestBody(RequestBody.create(MediaType.parse("image/jpg"), file))
                .compose(TransformUtil.<UpLoadResponse>applySchedulerTransformer())
                .subscribe(observer);
    }

    @Override
    public void multiFileUpload(final File[] files, Observer<UpLoadResponse> observer) {
        mService.multiFileRequestBody(new HashMap<String, RequestBody>() {
            {
                for (File file : files) {

                    put("file\";filename=\"" + file.getName(),
                            RequestBody.create(MediaType.parse("image/jpg"), file));
                }
            }
        }).compose(TransformUtil.<UpLoadResponse>applySchedulerTransformer())
                .subscribe(observer);
    }

    @Override
    public void multiUpload(File file, String text, Observer<UpLoadResponse> observer) {
        mService.multiRequestBody(RequestBody.create(MediaType.parse("image/jpg"), file), text)
                .compose(TransformUtil.<UpLoadResponse>applySchedulerTransformer())
                .subscribe(observer);
    }
}
