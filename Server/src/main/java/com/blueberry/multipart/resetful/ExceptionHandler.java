package com.blueberry.multipart.resetful;


import com.blueberry.multipart.entity.ErrorResponse;
import com.blueberry.multipart.exception.MultipartException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 7/6/2017.
 */
@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(MultipartException.class)
    @ResponseBody
    public ErrorResponse handleMultipartException(MultipartException e, HttpServletResponse response) {
        response.setStatus(e.code());
        return new ErrorResponse(e.code(), e.message());
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(2000, "未知异常");
    }
}
