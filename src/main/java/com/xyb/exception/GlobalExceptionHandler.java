package com.xyb.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lian
 * @Date 2018/3/20
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public RestInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        RestInfo<String> r = new RestInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(RestInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}
