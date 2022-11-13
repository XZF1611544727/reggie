package com.juct.reggie.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juct.reggie.common.R;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.constant.EmployeeConstant;
import com.juct.reggie.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 谢智峰
 * @create 2022-11-12 13:43
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session域中获取emp对象
        Employee emp = (Employee) request.getSession().getAttribute(EmployeeConstant.SESSION);
        //如果存在则说明已登录
        if (emp != null) {
            //把emp对象放入到ThreadLocal中
            ThreadLocalUtil.set(emp);
            return true;
        }
        log.error("用户未登录，非法访问：{}", request.getRequestURI());
        //emp为空，说明没有登录，返回未登录结果
        R<Object> error = R.error(EmployeeConstant.STATUS_NOT_LOGIN);
        //转化为JSON
        String result = new ObjectMapper().writeValueAsString(error);
        //返回给前端
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(result);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadLocalUtil.remove();
    }

/*    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }*/
}
