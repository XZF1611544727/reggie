package com.juct.reggie.mapper;

import com.juct.reggie.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-12 11:11
 */
@Mapper
public interface EmployeeMapper {
    List<Employee> list();

    Employee selectByUsername(String username);
}
