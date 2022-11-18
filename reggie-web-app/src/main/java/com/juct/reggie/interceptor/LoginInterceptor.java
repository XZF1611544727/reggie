package com.juct.reggie.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juct.reggie.common.R;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.constant.EmployeeConstant;
import com.juct.reggie.constant.UserConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 谢智峰
 * @create 2022-11-18 13:27
 */
/*
登录检查拦截器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 从session中获取员工id
        Long uid = (Long) request.getSession().getAttribute(UserConstant.SESSION);
        //判断：如果有，说明登录了，放行
        if (uid != null){
            // 打印当前线程信息
            log.info("------> 拦截器，当前线程：{}",Thread.currentThread());
            // ** 将当前用户放入当前线程副本中 ** //
            ThreadLocalUtil.set(uid);

            return true;
        }

        log.error("用户未登录，非法访问：{}", request.getRequestURI());
        //emp为空，说明没有登录，返回未登录结果
        String result = new ObjectMapper().writeValueAsString(R.error(EmployeeConstant.STATUS_NOT_LOGIN));
        response.getWriter().write(result);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // ** 从线程副本中移除值  ** //
        ThreadLocalUtil.remove();
    }
}