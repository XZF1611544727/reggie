package com.juct.reggie.controller;

/**
 * @author 谢智峰
 * @create 2022-11-14 13:32
 */

import com.aliyun.oss.internal.OSSUtils;
import com.juct.reggie.common.R;
import com.juct.reggie.common.util.OssTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private OssTemplate ossTemplate;

    @PostMapping("/upload")
    public R<String> upload(
            //springmvc提供的文件上传类
            MultipartFile file
    ) throws IOException {
        //获取带后缀的文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件输入流
        InputStream inputStream = file.getInputStream();
        String url = ossTemplate.upload(originalFilename, inputStream);
        return R.success(url);
    }
}
