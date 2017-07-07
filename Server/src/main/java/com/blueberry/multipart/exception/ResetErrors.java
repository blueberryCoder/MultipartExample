package com.blueberry.multipart.exception;

/**
 * Created by Administrator on 7/6/2017.
 */
public enum ResetErrors {

    INVALID_ARGUMENT(1001);

    private int code;

    ResetErrors(int code) {
        this.code = code;
    }

    public MultipartException whit(String message) {
        return new MultipartException(this.code, message);
    }
}
