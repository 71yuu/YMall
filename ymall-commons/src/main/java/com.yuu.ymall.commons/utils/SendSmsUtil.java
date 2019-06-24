package com.yuu.ymall.commons.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author by Yuu
 * @classname SendSmsUtil
 * @date 2019/6/24 9:06
 */
public class SendSmsUtil {

    /**
     * 阿里云短信 accesskey_id
     */
    private static final String ACCESSKEYID = "LTAIF9a6WQp8ubSP";

    /**
     * 阿里云短信 secret
     */
    private static final String SECRET = "VL5pg3reFZcWckwS9uwyCy5XP64Yll";

    /**
     * 阿里云短信签名名称
     */
    private static final String SIGNNAME = "YMall";

    /**
     * 阿里云短信模板Code
     */
    private static final String TEMPLATECODE = "SMS_168592535";

    /**
     * 阿里云短信模板变量对应的实际值，JSON 格式
     */
    private static final String TEMPLATEPARAM = "{\"code\":\"123\"}";

    /**
     * 发送短信
     *
     * @param phone 手机号
     * @return
     */
    public static String sendSms(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("default", ACCESSKEYID, SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", SIGNNAME);
        request.putQueryParameter("TemplateCode", TEMPLATECODE);
        String numeric = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
        request.putQueryParameter("TemplateParam", "{\"code\":\""+ numeric +"\"}");
        String result = "";
        try {
            CommonResponse response = client.getCommonResponse(request);
            result = response.getData();
            System.out.println(result);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return numeric;
    }
}
