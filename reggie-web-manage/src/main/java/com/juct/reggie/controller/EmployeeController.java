package com.juct.reggie.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.R;
import com.juct.reggie.constant.EmployeeConstant;
import com.juct.reggie.domain.Employee;
import com.juct.reggie.service.EmployeeService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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

    @PutMapping
    public R updateEmployee(@RequestBody Employee employee,
                            HttpSession session
    ) {
        //前端信息校验
        if (employee.getId() == null) {
            return R.error("ID不能为空");
        }
        employee.setUpdateTime(LocalDateTime.now());
        Employee emp = (Employee) session.getAttribute(EmployeeConstant.SESSION);
        employee.setUpdateUser(emp.getId());
        employeeService.updateById(employee);
        return R.success(null);
    }


    /**
     * 新增员工
     *
     * @param employee
     * @param session
     * @return
     */
    @PostMapping
    public R addEmployee(@RequestBody Employee employee,
                         HttpSession session
    ) {
        //前端信息校验
        String idNumber = employee.getIdNumber();
        String name = employee.getName();
        String phone = employee.getPhone();
        String username = employee.getUsername();
        if (StrUtil.isBlank(idNumber) || StrUtil.isBlank(name) || StrUtil.isBlank(phone) || StrUtil.isBlank(username)) {
            return R.error("信息不完整");
        }
        Employee emp = employeeService.selectByUsername(username);
        //查询用户名是否注册
        if (emp != null) {
            return R.error("该用户名已存在");
        }
        //查询手机号是否注册
        if (employeeService.selectByPhone(phone) != null) {
            return R.error("该手机号已注册");
        }
        //查询身份证号码是否注册
        if (employeeService.selectByIdNumber(idNumber) != null) {
            return R.error("该身份证号已注册");
        }
        //雪花算法生成ID
        employee.setId(IdUtil.getSnowflakeNextId());
        //MD5加密默认密码123456
        employee.setPassword(SecureUtil.md5("123456"));
        //session域中获取创建者信息
        Employee create = (Employee) session.getAttribute(EmployeeConstant.SESSION);
        employee.setCreateUser(create.getId());
        employee.setUpdateUser(create.getId());
        //保存员工
        employeeService.addEmployee(employee);
        return R.success(null);
    }


    /**
     * 用户登录
     *
     * @param employee
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpSession session) {
        String username = employee.getUsername();
        String password = employee.getPassword();
        if (StrUtil.isAllBlank(username, password)) {//判断用户名密码是否为空
            return R.error("用户名或密码不能为空");
        }
        Employee emp = employeeService.selectByUsername(username);
        if (emp == null) {//判断用户是否存在
            return R.error("该用户还未注册！");
        }
        if (emp.getStatus() == 0) {//判断用户是否被禁用
            return R.error("该用户已被禁用");
        }
        if (!emp.getPassword().equals(SecureUtil.md5(password))) {//判断密码是否一致
            return R.error("密码错误！");
        }
        session.setAttribute("employee", emp);//将emp存到session域中
        Employee result = new Employee();//返回给前端需要的信息
        result.setId(emp.getId());
        result.setName(emp.getName());
        result.setUsername(emp.getUsername());
        return R.success(result);
    }

    /**
     * 用户退出
     *
     * @param session
     * @return
     */
    @PostMapping("logout")
    public R logout(HttpSession session) {
        session.invalidate();
        return R.success(null);
    }

    /**
     * 分页查询
     *
     * @param page     当前页
     * @param pageSize 每页条数
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<PageInfo<Employee>> selectByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                              String name) {
        return R.success(employeeService.selectByPage(page, pageSize, name));
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> selectById(@PathVariable String id) {
        return R.success(employeeService.selectById(id));
    }

}
