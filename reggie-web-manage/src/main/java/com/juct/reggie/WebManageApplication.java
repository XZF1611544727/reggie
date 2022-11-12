package com.juct.reggie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 谢智峰
 * @create 2022-11-12 10:59
 */
@SpringBootApplication//主类
@MapperScan("com.juct.reggie.mapper")//指定mybatis类所在的包
@EnableTransactionManagement //开启对事物管理的支持
@Slf4j
public class WebManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebManageApplication.class, args);
        log.info("项目启动成功");
    }
}
