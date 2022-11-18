package com.juct.reggie.controller;

import cn.hutool.core.util.RandomUtil;
import com.juct.reggie.common.R;
import com.juct.reggie.common.exception.BusinessException;
import com.juct.reggie.common.util.SmsTemplate;
import com.juct.reggie.constant.UserConstant;
import com.juct.reggie.domain.User;
import com.juct.reggie.service.UserService;
import com.juct.reggie.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 谢智峰
 * @create 2022-11-18 19:56
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R sendMsg(
            @RequestBody UserVO userVO,
            HttpSession session
    ) {
        String phone = userVO.getPhone();
        //非空判断
        if (phone.isEmpty()) {
            return R.error("请输入手机号");
        }
        //生成验证码
        String code = RandomUtil.randomNumbers(6);
        //发送短信
        //String result = smsTemplate.sendSms(phone, code);
        String result = "200";
        if(result.equals("200") || result.equals("OK")){
            //4. 保存到session，便于后期对比 15666666666:code 123456
            log.info("phone = {} code = {}", phone,code);
            session.setAttribute(phone+":code",code);
            //5. 返回结果
            return R.success(null);
        }
        return R.error("短信发送失败，请检查手机号码");
    }

    @PostMapping("/login")
    public R login(
            @RequestBody UserVO userVO,
            HttpSession session
    ) {
        String phone = userVO.getPhone();
        //非空判断
        if (phone.isEmpty()) {
            return R.error("手机号不能为空");
        }
        Object codeInSession = session.getAttribute(phone+":code");
        log.info("codeInSession = {} ", codeInSession);
        if (codeInSession == null || !codeInSession.equals(userVO.getCode())) {
            throw new BusinessException(500, "验证码错误");
        }
        User user = userService.checkUser(phone);
        //到这里说明验证码正确 && 账号没有问题
        //5. 保存用户id到session
        session.setAttribute(UserConstant.SESSION,user.getId());
        return R.success(null);
    }
}
