package org.zmz.sb3.security.examples.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    public static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler({Exception.class})
    public CommonResponse<String> handlerException(Exception ex) {
        LOG.error("全局异常处理 handlerException # ", ex);
        return CommonResponse.fail(ex.getMessage());
    }

}
