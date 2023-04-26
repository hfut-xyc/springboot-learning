package com.demo.config;

import com.demo.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public Result handleException(HttpServletRequest request, Exception e) {
        log.error("{}: {}", request.getRequestURI(), e.getMessage());
        return Result.error(e.getMessage());
    }
}