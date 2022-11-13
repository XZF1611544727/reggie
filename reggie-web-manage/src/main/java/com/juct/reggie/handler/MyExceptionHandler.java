package com.juct.reggie.handler;

import com.juct.reggie.common.R;
import com.juct.reggie.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 谢智峰
 * @create 2022-11-13 20:25
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public R businessException(BusinessException e) {
        log.error("**** 业务异常：{}", e.getMessage());
        return R.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        //日志记录
        log.error("**** 程序异常：{}", e.getMessage());
        //短信提示
        System.out.println("发短信给运维/开发人员...");
        //返回友好提示
        return R.error("系统异常，请稍候重试");
    }
}
