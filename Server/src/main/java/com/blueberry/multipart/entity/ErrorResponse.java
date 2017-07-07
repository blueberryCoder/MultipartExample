package com.blueberry.multipart.entity;

/**
 * Created by Administrator on 7/6/2017.
 *
 */
public class ErrorResponse {
    private int code;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
