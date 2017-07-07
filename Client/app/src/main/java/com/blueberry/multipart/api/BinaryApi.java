package com.blueberry.multipart.api;

import com.blueberry.multipart.entity.UpLoadResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by blueberry on 7/7/2017.
 */

public interface BinaryApi {

    /**
     * binary方式上传，这种方式整个请求体直接就是二进制数据。服务端只需要拿到request#iputsteam就可以获得。
     *
     * @param body
     * @return
     */
    @POST("upload/binary")
    Observable<UpLoadResponse> binary(@Body RequestBody body);
}
