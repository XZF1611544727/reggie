package com.juct.reggie.service;

import com.juct.reggie.domain.Employee;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-12 11:15
 */
public interface EmployeeService {
    List<Employee> list();

    Employee selectByUsername(String username);
}
