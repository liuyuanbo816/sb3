package org.zmz.sb3.redis.seckill.common;

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

    public static <E> R<E> success() {
        return new R<>();
    }

    public static <E> R<E> success(E data) {
        return new R<>(data);
    }

    public static R<String> fail(String msg) {
        return new R<>(500, msg);
    }

    public static R<String> fail(CodeMsg codeMsg) {
        return new R<>(codeMsg.getCode(), codeMsg.getMsg());
    }
}