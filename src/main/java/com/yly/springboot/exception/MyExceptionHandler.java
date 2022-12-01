package com.yly.springboot.exception;

import com.yly.springboot.common.Result;
import com.yly.springboot.common.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 给异常设置返回消息，然后将异常返回给前端
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se){
        return new ResultUtil<>().setErrorMsg(se.getCode(),se.getMessage());
    }
}
