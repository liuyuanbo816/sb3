package org.zjc.smalltools.common;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public List<Map<String, String>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors().stream()
                .map(err -> Map.of(err.getField(), Objects.requireNonNull(err.getDefaultMessage())))
                .toList();
    }

    @ExceptionHandler(value = {Exception.class})
    public R<String> handlerEx(Exception ex) {
        return R.fail(ex.getMessage());
    }

}