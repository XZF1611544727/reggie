package com.juct.reggie.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.juct.reggie.common.R;
import com.juct.reggie.domain.Employee;
import com.juct.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-12 11:21
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/list")
    public List<Employee> list() {
        return employeeService.list();
    }

    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpSession session) {
        String username = employee.getUsername();
        String password = employee.getPassword();
        if (StrUtil.isAllBlank(username,password)) {
            return R.error("用户名或密码不能为空");
        }
        Employee emp = employeeService.selectByUsername(username);
        if (emp == null) {
            return R.error("该用户还未注册！");
        }
        if (emp.getStatus() == 0) {
            return R.error("该用户已被禁用");
        }
        if (!emp.getPassword().equals(SecureUtil.md5(password))) {
            return R.error("密码错误！");
        }
        session.setAttribute("employee",emp);
        Employee result = new Employee();
        result.setId(emp.getId());
        result.setName(emp.getName());
        result.setUsername(emp.getUsername());
        return R.success(result);
    }

    @PostMapping("logout")
    public R logout(HttpSession session) {
        session.invalidate();
        return R.success(null);
    }
}
