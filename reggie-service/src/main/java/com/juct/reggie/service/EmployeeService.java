package com.juct.reggie.service;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.domain.Employee;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-12 11:15
 */
public interface EmployeeService {

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return
     */
    Employee selectByUsername(String username);

    /**
     * 根据身份证号查询
     * @param idNumber 身份证号
     * @return
     */
    Employee selectByIdNumber(String idNumber);

    /**
     * 根据手机号查询
     * @param phone 手机号
     * @return
     */
    Employee selectByPhone(String phone);

    /**
     * 新增
     * @param employee
     */
    void addEmployee(Employee employee);


    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Employee> selectByPage(Integer page, Integer pageSize, String name);

    /**
     * Id查询
     * @param id
     * @return
     */
    Employee selectById(String id);

    /**
     * 根据ID更新
     * @param employee
     */
    void updateById(Employee employee);
}
