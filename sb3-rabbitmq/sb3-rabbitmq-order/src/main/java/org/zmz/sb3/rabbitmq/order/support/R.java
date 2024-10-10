package org.zmz.sb3.rabbitmq.order.support;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class R<T> {
    private String code;
    private String msg;
    private T data;

    public R() {
        this("0000", "ok");
    }

    public R(String code, String msg) {
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
        return new R<>("9999", msg);
    }
}
