package com.juct.reggie.handler;

import com.juct.reggie.common.R;
import com.juct.reggie.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 谢智峰
 * @create 2022-11-18 13:21
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    //处理业务异常
    @ExceptionHandler(BusinessException.class)
    public R handlerBusinessExceptio(BusinessException e){
        //日志记录
        log.error("**** 异常详情：{}",e);
        //返回友好提示
        return R.error(e.getMessage());
    }

    //处理程序异常（兜底异常处理）
    @ExceptionHandler(Exception.class)
    public Object handException(Exception e){
        //日志记录
        log.error("**** 异常详情：{}",e);
        //短信提示
        System.out.println("发短信给运维/开发人员...");
        //返回友好提示
        return R.error(e.getMessage());
    }
}
