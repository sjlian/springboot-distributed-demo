package com.lian.response;

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
    public RestInfo<String> jsonErrorHandler(HttpServletRequest req, Throwable t) {
        MyException myException = MyException.wrapIfNeeded(t);
        RestInfo<String> r = new RestInfo<>();
        r.setMessage(myException.getMessage());
        r.setCode(myException.getCode());
        r.setData("fail");
        r.setUrl(req.getRequestURI());
        return r;
    }
}
