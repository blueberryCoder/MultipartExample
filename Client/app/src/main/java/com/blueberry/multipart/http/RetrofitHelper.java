package com.blueberry.multipart.http;

import com.blueberry.multipart.config.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by blueberry on 7/6/2017.
 */

public class RetrofitHelper {

    private static volatile RetrofitHelper instance;

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    return new RetrofitHelper();
                }
            }
        }
        return instance;
    }

    private Retrofit mRetrofit;

    private RetrofitHelper() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
