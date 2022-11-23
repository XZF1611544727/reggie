package com.juct.reggie.controller;

import com.juct.reggie.common.R;
import com.juct.reggie.domain.AddressBook;
import com.juct.reggie.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-19 22:54
 */
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    AddressBookService addressBookService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public R<List<AddressBook>> selectBooks() {
        return R.success(addressBookService.list());
    }

    /**
     * 添加地址
     * @param addressBook
     * @return
     */
    @PostMapping
    public R addBook(@RequestBody AddressBook addressBook) {
        addressBookService.addBook(addressBook);
        return R.success("地址添加成功");
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<AddressBook> selectBookById(
            @PathVariable Long id
    ) {
        return R.success(addressBookService.selectBookById(id));
    }

    /**
     * 更新地址
     * @param addressBook
     * @return
     */
    @PutMapping
    public R updateBook(
            @RequestBody AddressBook addressBook
    ) {
        addressBookService.updateBook(addressBook);
        return R.success("地址更改成功");
    }

    /**
     * 更改默认地址
     */
    @PutMapping("/default")
    public R updateDefaultBook(@RequestBody AddressBook addressBook) {
        addressBookService.updateDefaultBook(addressBook);
        return R.success(null);
    }

    /**
     * 获取默认收货地址
     * @return
     */
    @GetMapping("/default")
    public R getDefaultAddress(){
        AddressBook defaultAddress = addressBookService.getDefaultAddress();
        if(defaultAddress != null){
            return R.success(defaultAddress);
        }
        return R.error("当前没有默认收货地址，请添加");
    }
}
