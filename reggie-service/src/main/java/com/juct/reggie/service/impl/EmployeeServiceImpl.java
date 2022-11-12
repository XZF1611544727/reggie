package com.juct.reggie.service.impl;

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

    @Override
    public List<Employee> list() {
        return employeeMapper.list();
    }

    @Override
    public Employee selectByUsername(String username) {
        return employeeMapper.selectByUsername(username);
    }
}
