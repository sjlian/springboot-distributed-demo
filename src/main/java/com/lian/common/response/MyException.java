package com.lian.common.response;

/**
 * @Author lian
 * @Date 2018/3/20
 */

public class MyException extends RuntimeException{
    //TODO 自定义状态码
    public static final Integer PARAM_ERROR = 101;
    public static final Integer INPUT_ERROR = 102;
    public static final Integer NOT_ALLOWED = 103;
    public static final Integer INTERNAL_ERROR = 500;


    private Integer code;
    private String message;
    private String url;

    public MyException() {
    }

    private MyException(Throwable throwable, Integer code, String message) {
        super(throwable);
        this.code = code;
        this.message = message;
    }
    public static MyException wrapIfNeeded(Throwable t) {
        if (t instanceof MyException) {
            return (MyException) t;
        } else {    //TODO 这里可以捕获各种异常，如主键冲突异常，权限验证异常等等然后转换成自定义通知。
            //所有自定义的异常都捕获不到，说明可能是系统内部错误导致的。
            return internalError(t, t.getMessage());
        }
    }

    //101 输入参数不规范
    public static MyException inputError(Throwable ex, String detail) {
        return new MyException(ex, MyException.INPUT_ERROR, detail);
    }

    //103 操作不允许
    public static MyException notAllowed(Throwable ex, String detail) {
        return new MyException(ex, MyException.NOT_ALLOWED, detail);
    }

    // 5开头的为系统内部错误，不需要程序员去捕获5开头的异常，客户端返回值出现该异常一定是开发者bug。
    public static MyException internalError(Throwable ex, String detail) {
        return new MyException(ex, MyException.INTERNAL_ERROR, detail);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
