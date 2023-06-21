package org.javaboy.common.exception;

import org.javaboy.common.RespBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 15:03
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespBean runtimeException(RuntimeException e) {
        return RespBean.error(e.getMessage());
    }
}
