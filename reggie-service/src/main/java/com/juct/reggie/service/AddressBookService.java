package com.juct.reggie.service;

import com.juct.reggie.domain.AddressBook;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-19 22:55
 */
public interface AddressBookService {
    //查询所有
    List<AddressBook> selectBooks();

    //新增
    void addBook(AddressBook addressBook);

    //根据ID查询
    AddressBook selectBookById(Long id);

    //更新
    void updateBook(AddressBook addressBook);

    //设置默认地址
    void updateDefaultBook(AddressBook addressBook);

    //查询默认收货地址
    AddressBook getDefaultAddress();
}
