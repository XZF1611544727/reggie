package com.juct.reggie.service.impl;

import cn.hutool.core.util.IdUtil;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.domain.AddressBook;
import com.juct.reggie.mapper.AddressBookMapper;
import com.juct.reggie.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-19 22:55
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    AddressBookMapper addressBookMapper;

    /**
     * 查询当前用户的收获地址
     * @return
     */
    @Override
    public List<AddressBook> list() {
        //1. 获取当前用户id
        Long uid = (Long) ThreadLocalUtil.get();
        //2. 根据id查询收货地址
        return addressBookMapper.list(uid);
    }

    /**
     * 添加地址
     * @param addressBook
     */
    @Override
    public void addBook(AddressBook addressBook) {
        addressBook.setId(IdUtil.getSnowflakeNextId());
        addressBook.setUserId((Long) ThreadLocalUtil.get());
        addressBook.setCreateUser((Long) ThreadLocalUtil.get());
        addressBook.setUpdateUser((Long) ThreadLocalUtil.get());
        addressBookMapper.addBook(addressBook);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public AddressBook selectBookById(Long id) {
        return addressBookMapper.selectBookById(id);
    }

    /**
     * 更新
     * @param addressBook
     */
    @Override
    public void updateBook(AddressBook addressBook) {
        addressBook.setUpdateUser((Long) ThreadLocalUtil.get());
        addressBook.setUpdateTime(LocalDateTime.now());
        addressBookMapper.updateBookById(addressBook);
    }

    /**
     * 设置默认地址
     * @param addressBook
     */
    @Transactional
    @Override
    public void updateDefaultBook(AddressBook addressBook) {
        //1. 当前用户的收获地址都设置为非默认
        addressBookMapper.allNotDefault((Long) ThreadLocalUtil.get());
        //2. 更新当前id为默认
        addressBookMapper.updateDefaultBook(addressBook.getId());
    }

    /**
     * 查询默认收货地址
     * @return
     */
    @Override
    public AddressBook getDefaultAddress() {
        return addressBookMapper.getDefaultAddress((Long) ThreadLocalUtil.get());
    }

}
