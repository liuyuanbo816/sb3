package org.zmz.sb3.rabbitmq.order.remote;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UspResponse<T> {
    private Integer code;
    private String msg;
    private T data;
}
