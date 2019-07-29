package com.lian.common.response;

/**
 * @Author lian
 * @Date 2018/3/20
 */
public class RestInfo<T> {
    private final Integer OK = 0;
    private final String SUCCESS = "success";

    private Integer code;
    private String message;
    private String url;
    private T data;

    public RestInfo() {
    }

    public RestInfo(T data) {
        this.data = data;
        this.code = OK;
        this.message = SUCCESS;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
