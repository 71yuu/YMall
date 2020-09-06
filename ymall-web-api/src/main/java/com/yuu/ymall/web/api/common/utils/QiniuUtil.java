package com.yuu.ymall.web.api.common.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import com.yuu.ymall.commons.execption.YmallUploadException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 七牛云图片上传工具类
 * @Classname QiniuUtil
 * @Date 2019/5/24 20:38
 * @Created by Yuu
 */
public class QiniuUtil {

    private final static Logger log = LoggerFactory.getLogger(QiniuUtil.class);

    /**
     * 生成上传凭证，然后准备上传
     */
    private static String accessKey = "";
    private static String secretKey = "";
    private static String bucket = "ymall";
    private static String origin = "";
    private static Auth auth = Auth.create(accessKey, secretKey);

    public static String getUpToken() {
        return auth.uploadToken(bucket, null, 3600, new StringMap().put("insertOnly", 1));
    }

    public static String qiniuBase64Upload(String data64){

        String key = renamePic(".png");
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //服务端http://up-z2.qiniup.com
        String url = "http://up-z2.qiniup.com/putb64/-1/key/"+ UrlSafeBase64.encodeToString(key);
        RequestBody rb = RequestBody.create(null, data64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return origin+key;
    }

    public static String base64Data(String data){

        if(data==null||data.isEmpty()){
            return "";
        }
        String base64 =data.substring(data.lastIndexOf(",")+1);
        return base64;
    }

    /**
     * 以时间戳重命名
     * @param fileName
     * @return
     */
    public static String renamePic(String fileName){
        String extName = fileName.substring(fileName.lastIndexOf("."));
        return System.currentTimeMillis()+extName;
    }

    public static String isValidImage(HttpServletRequest request, MultipartFile file){
        //最大文件大小
        long maxSize = 5242880;
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        if(!ServletFileUpload.isMultipartContent(request)){
            return "请选择文件";
        }

        if(file.getSize() > maxSize){
            return "上传文件大小超过5MB限制";
        }
        //检查扩展名
        String fileName=file.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if(!Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
            return "上传文件扩展名是不允许的扩展名\n只允许" + extMap.get("image") + "格式";
        }

        return "valid";
    }

    public static String checkExt(String fileName,String dirName){
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");

        //检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
            return "上传文件扩展名是不允许的扩展名\n只允许" + extMap.get(dirName) + "格式";
        }
        return "valid";
    }

    public static void main(String[] args){
        base64Data("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD/2");
    }
}
