package com.juct.reggie;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 谢智峰
 * @create 2022-11-12 16:05
 */
@SpringBootTest
@Slf4j
public class WebManageApplicationTests {
    @Test
    public void idUtilTest(){
        long snowflakeNextId = IdUtil.getSnowflakeNextId();
        log.info("snowflakeNextId = {} ", snowflakeNextId);
    }
}
