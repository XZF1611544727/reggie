package com.juct.reggie.common.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@ConfigurationProperties(prefix = "sms.syhj")
@Getter
@Setter
public class SmsTemplate {

    //可以配置项-改成自己的
    private String appcode = "f97c5cbafb014065abb72d6c41131725";
    private String templateId = "TPL_09209";

    public String sendSms(String phone,String code){
        //固定配置
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        //注意对上模板的参数名，多个用逗号隔开
        bodys.put("content", "code:"+code+",expire_at:5");
        bodys.put("phone_number", phone);
        bodys.put("template_id", templateId);


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            String status = response.getStatusLine().getStatusCode()+"";
            if (status.equals("200") || status.equals("OK")){
                log.info("----> {} 短信发送成功，验证码 {}！",phone,code);
            }else {
                log.info("----> 短信发送失败 {}",response.getStatusLine());
            }
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "200";
    }
}
