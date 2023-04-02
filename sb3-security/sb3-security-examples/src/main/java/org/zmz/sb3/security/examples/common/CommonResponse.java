package org.zmz.sb3.security.examples.common;

public class CommonResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public CommonResponse() {
        this(100000, "Success");
    }

    public CommonResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse(T data) {
        this();
        this.data = data;
    }

    public static <E> CommonResponse<E> success(E data) {
        return new CommonResponse<>(data);
    }

    public static CommonResponse<?> fail(Integer code, String errorMsg) {
        return new CommonResponse<>(code, errorMsg);
    }

    public static CommonResponse<?> fail(String errorMsg) {
        return fail(999999, errorMsg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
