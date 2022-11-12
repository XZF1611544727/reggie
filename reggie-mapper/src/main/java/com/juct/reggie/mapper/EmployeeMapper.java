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

    //根据用户名查询
    Employee selectByUsername(String username);

    //根据身份证号查询
    Employee selectByIdNumber(String idNumber);

    //根据手机号查询
    Employee selectByPhone(String phone);

    //保存
    void addEmployee(Employee employee);

    //查询所有
    //@Select("select id,name,username,password,phone,sex,id_number,status,create_time,update_time,create_user,update_user from employee")
    List<Employee> selectAll(String name);

    //根据id查询
    Employee selectById(String id);

    //根据ID修改
    void updateById(Employee employee);
}
