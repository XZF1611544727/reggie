package com.juct.reggie.mapper;

import com.juct.reggie.domain.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-19 22:56
 */
@Mapper
public interface AddressBookMapper {

    //查询所有
    @Select("select * from address_book")
    List<AddressBook> selectBooks();

    //新增
    void addBook(AddressBook addressBook);

    //更新
    void updateBookById(AddressBook addressBook);

    //根据ID查询
    @Select("select * from address_book where id = #{id}")
    AddressBook selectBookById(Long id);

    @Update("update address_book set is_default = 1 where id = #{id}")
    void updateDefaultBook(Long id);

    @Update("update address_book set is_default = 0 where user_id = #{userId}")
    void allNotDefault(Long aLong);

    @Select("select * from address_book where user_id = #{uid} and is_default = 1 and is_deleted = 0")
    AddressBook getDefaultAddress(Long uid);
}
