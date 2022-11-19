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
     *
     * @return
     */
    @GetMapping("/list")
    public R<List<AddressBook>> selectBooks() {
        return R.success(addressBookService.selectBooks());
    }

    /**
     * 添加地址
     *
     * @param addressBook
     * @return
     */
    @PostMapping
    public R addBook(@RequestBody AddressBook addressBook) {
        addressBookService.addBook(addressBook);
        return R.success(null);
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
        return R.success(null);
    }

    @PutMapping("/default")
    public R updateDefaultBook(@RequestBody AddressBook addressBook) {
        addressBookService.updateDefaultBook(addressBook);
        return R.success(null);
    }
}
