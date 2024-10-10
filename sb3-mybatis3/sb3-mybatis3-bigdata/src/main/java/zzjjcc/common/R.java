package zzjjcc.common;

public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public R() {
        this(200, "ok");
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(T data) {
        this();
        this.data = data;
    }

    public static <E> R<E> ok() {
        return new R<>();
    }

    public static <E> R<E> ok(E data) {
        return new R<>(data);
    }

    public static R<String> fail(String msg) {
        return new R<>(500, msg);
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
