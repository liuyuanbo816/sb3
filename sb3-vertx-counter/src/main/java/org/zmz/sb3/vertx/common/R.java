package org.zmz.sb3.vertx.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
