package com.blueberry.multipart.model;

import com.blueberry.multipart.api.BinaryApi;
import com.blueberry.multipart.entity.UpLoadResponse;
import com.blueberry.multipart.http.RetrofitHelper;
import com.blueberry.multipart.util.RequestBodyUtil;
import com.blueberry.multipart.util.TransformUtil;

import java.io.File;
import java.io.InputStream;

import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by blueberry on 7/7/2017.
 */

public class BinaryRepository {
    private BinaryRepository(){}

    public static BinaryRepository newInstance() {
        return new BinaryRepository();
    }

    public void uploadBinary(File file, Observer<UpLoadResponse> observer){
        RetrofitHelper.getInstance()
                .getRetrofit()
                .create(BinaryApi.class)
                .binary(RequestBody.create(MediaType.parse("image/jpg"),file))
                .compose(TransformUtil.<UpLoadResponse>applySchedulerTransformer())
                .subscribe(observer);
    }

    public void uploadBinary(InputStream input, Observer<UpLoadResponse> observer){
        RetrofitHelper.getInstance()
                .getRetrofit()
                .create(BinaryApi.class)
                .binary(RequestBodyUtil.create(MediaType.parse("image/jpg"),input))
                .compose(TransformUtil.<UpLoadResponse>applySchedulerTransformer())
                .subscribe(observer);
    }
}
