package org.zjc.smalltools.common;

public class R<T> {
    private String code;
    private String msg;
    private T data;

    public R(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R() {
        this("000000", "");
    }

    public R(T data) {
        this();
        this.data = data;
    }

    public R(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> ok(T data) {
        return new R<>(data);
    }

    public static R<String> fail(String msg) {
        return new R<>("999999", msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
