package com.juct.reggie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juct.reggie.domain.Employee;
import com.juct.reggie.mapper.EmployeeMapper;
import com.juct.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-12 11:15
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return
     */
    @Override
    public Employee selectByUsername(String username) {
        return employeeMapper.selectByUsername(username);
    }

    /**
     * 根据身份证号查询
     * @param idNumber 身份证号
     * @return
     */
    @Override
    public Employee selectByIdNumber(String idNumber) {
        return employeeMapper.selectByIdNumber(idNumber);
    }

    /**
     * 根据手机号查询
     * @param phone 手机号
     * @return
     */
    @Override
    public Employee selectByPhone(String phone) {
        return employeeMapper.selectByPhone(phone);
    }

    /**
     * 新增
     * @param employee
     */
    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }


    /**
     * @param page     当前页
     * @param pageSize 每页数量
     * @return
     */
    @Override
    public PageInfo<Employee> selectByPage(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<Employee> list = employeeMapper.selectAll(name);
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Employee selectById(String id) {
        return employeeMapper.selectById(id);
    }

    /**
     * 根据ID修改
     * @param employee
     */
    @Override
    public void updateById(Employee employee) {
        employeeMapper.updateById(employee);
    }


}
