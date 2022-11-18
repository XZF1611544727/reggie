package com.juct.reggie;

import com.sun.glass.ui.Application;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 谢智峰
 * @create 2022-11-18 13:04
 */
@SpringBootApplication
@MapperScan("com.juct.reggie.mapper")
@Slf4j
@EnableTransactionManagement
public class WebAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class);
        log.info("项目启动成功");
        log.info("15337218453");
    }
}
