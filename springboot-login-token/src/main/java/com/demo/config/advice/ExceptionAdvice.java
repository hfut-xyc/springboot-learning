package com.demo.config.advice;

import com.demo.entity.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(HttpServletRequest request, Exception e) {
        log.error("{}: {}", request.getRequestURI(), e.getMessage());
        return Result.error(e.getMessage());
    }
}