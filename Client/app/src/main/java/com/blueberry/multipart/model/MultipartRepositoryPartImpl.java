package com.blueberry.multipart.model;

import com.blueberry.multipart.api.MultipartApi;
import com.blueberry.multipart.entity.UpLoadResponse;
import com.blueberry.multipart.http.RetrofitHelper;
import com.blueberry.multipart.util.TransformUtil;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by blueberry on 7/6/2017.
 */

public class MultipartRepositoryPartImpl implements MultipartRepository {

    private MultipartApi mService;

    public static MultipartRepositoryPartImpl newInstance() {
        return new MultipartRepositoryPartImpl();
    }

    private MultipartRepositoryPartImpl() {
        mService = RetrofitHelper
                .getInstance()
                .getRetrofit()
                .create(MultipartApi.class);
    }

    @Override
    public void singleFileUpload(File file, Observer<UpLoadResponse> observer) {
        mService.singlePart(MultipartBody.Part
                .createFormData("file", "temp.jpg",
                        RequestBody.create(MediaType.parse("image/jpg"), file)))
                .compose(TransformUtil.<UpLoadResponse>applySchedulerTransformer())
                .subscribe(observer);
    }

    @Override
    public void multiFileUpload(final File[] files, Observer<UpLoadResponse> observer) {
        mService.multiFilePart(new ArrayList<MultipartBody.Part>() {
            {
                for (File file : files) {
                    add(MultipartBody.Part.createFormData("file", file.getName(),
                            RequestBody.create(MediaType.parse("image/jpg"), file)));
                }
            }
        }).compose(TransformUtil.<UpLoadResponse>applySchedulerTransformer())
                .subscribe(observer);
    }

    @Override
    public void multiUpload(File file, String text, Observer<UpLoadResponse> observer) {
        mService.multiPart(MultipartBody.Part.createFormData("file", file.getName(), RequestBody
                .create(MediaType.parse("image/jpg"), file)), text)
                .compose(TransformUtil.<UpLoadResponse>applySchedulerTransformer())
                .subscribe(observer);
    }
}
