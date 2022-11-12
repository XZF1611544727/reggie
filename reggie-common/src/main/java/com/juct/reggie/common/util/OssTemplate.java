package com.juct.reggie.common.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@ConfigurationProperties(prefix = "oss")
@Data
public class OssTemplate {
    // 默认配置
    // Endpoint
    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey
    private String accessKeyId = "LTAI5tDSd1z448qaV41PjJzV";
    private String accessKeySecret = "6gPDwUmAmH3WVp9O9W2MsyKWbOHnEQ";
    // Bucket名称
    private String bucketName = "reggie-web";
    // Bucket 域名
    private String buckentPoint = "https://reggie-web.oss-cn-beijing.aliyuncs.com/";

    //文件上传
    public String upload(String fileName, InputStream inputStream) {

        //设置文件最终的路径和名称
        String objectName = "images/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                + "/" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {

            // meta设置请求头,解决访问图片地址直接下载
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));

            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, inputStream,meta);

        } catch (OSSException oe) {
            System.out.println("Error Message:" + oe.getErrorMessage());
        } catch (ClientException ce) {
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }

        }
        //返回外网访问路径
        return buckentPoint+"/"+objectName;
    }

    public static String getContentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        return "image/jpg";
    }

}
