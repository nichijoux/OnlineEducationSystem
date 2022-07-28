package com.zh.oes.common.base.exception;

import com.zh.oes.common.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 所有异常都能被该函数处理
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.failure().message("系统异常");
    }

    // 自定义异常处理
    @ExceptionHandler(value = OESException.class)
    public Result error(OESException e) {
        e.printStackTrace();
        return Result.failure().message(e.getMessage()).code(e.getCode());
    }

    // 实体校验异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.failure().message(objectError.getDefaultMessage());
    }

    // 断言异常
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) {
        return Result.failure().message(e.getMessage());
    }

    // 运行时异常
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) {
        return Result.failure().message(e.getMessage());
    }
}