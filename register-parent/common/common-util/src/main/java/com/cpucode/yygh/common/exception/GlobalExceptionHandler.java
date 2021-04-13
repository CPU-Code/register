package com.cpucode.yygh.common.exception;

import com.cpucode.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : cpucode
 * @date : 2021/4/13
 * @time : 23:31
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result error(YyghException e) {
        e.printStackTrace();
        return Result.fail();
    }
}
