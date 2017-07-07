package com.blueberry.multipart.api;

import com.blueberry.multipart.entity.UpLoadResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by blueberry on 7/6/2017.
 * <p>
 * 如果使用Multipart.Part需要注意 @Part直接中不要有参数。
 * 如果使用RequestBody需要注意：如果上传文件name应该包含有filename（文件名）这个字段，负责后台可能会出错，
 * 比如我们要上传一个文件我们的name值应该为：file";filename="image.jpg;注意前后没有双引号，中间有2个双引号，
 * 这是因为Retrofit会自动帮我们拼接Content-Disposition;它拼接的方式为 form-data; name="我们设置的值"，如
 * 果用MultipartBody.Part我们则不需要这么费事的拼接，因为Multipart.Part.createFormData()放我们完成了该操
 * 作；
 * {@link okhttp3.MultipartBody.Part#createFormData(String, String, RequestBody)}
 */

public interface MultipartApi {

    /**
     * 单个文件上传
     */
    String SINGLE = "upload/single";

    /**
     * 多个文件上传（使用同一个字段名）
     */
    String MULTI_FILE = "upload/multi_file";

    /**
     * 文件+文本一起上传
     */
    String MULTI = "upload/multi";


    /**
     * 单个文件上传，使用MultipartBody.Part。
     *
     * @param part
     * @return
     */
    @Multipart
    @POST(SINGLE)
    Observable<UpLoadResponse> singlePart(@Part MultipartBody.Part part);

    /**
     * 单个文件上传，使用RequestBody。
     *
     * @param body
     * @return
     */
    @Multipart
    @POST(SINGLE)
    Observable<UpLoadResponse> singleRequestBody(@Part("file\";filename=\"image.jpg") RequestBody body);


    /**
     * 多文件上传使用 List<MultipartBody.Part>。
     *
     * @param parts
     * @return
     */
    @Multipart
    @POST(MULTI_FILE)
    Observable<UpLoadResponse> multiFilePart(@Part List<MultipartBody.Part> parts);

    /**
     * 多文件上传使用 HashMap<String, RequestBody> map。
     *
     * @param map
     * @return
     */
    @Multipart
    @POST(MULTI_FILE)
    Observable<UpLoadResponse> multiFileRequestBody(@PartMap HashMap<String, RequestBody> map);


    /**
     * 文件+文本上传。文件使用 RequestBody
     *
     * @param body
     * @param string
     * @return
     */
    @Multipart
    @POST(MULTI)
    Observable<UpLoadResponse> multiRequestBody(@Part("file\";filename=\"image.jpg") RequestBody body,
                                                @Part("text") String string);

    /**
     * 文件+文本上传。文件使用 MultipartBody.Part上传
     *
     * @param part
     * @param string
     * @return
     */
    @Multipart
    @POST(MULTI)
    Observable<UpLoadResponse> multiPart(@Part MultipartBody.Part part,
                                         @Part("text") String string);

}
