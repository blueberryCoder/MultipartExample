package com.blueberry.multipart.exception;

/**
 * Created by Administrator on 7/6/2017.
 */
public class MultipartException extends RuntimeException {

    private int code;

    public MultipartException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.getMessage();
    }

}
