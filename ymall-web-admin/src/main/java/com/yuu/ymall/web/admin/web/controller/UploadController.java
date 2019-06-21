package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.web.admin.commons.utils.QiniuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname UploadController
 * @Date 2019/5/24 20:21
 * @Created by Yuu
 */
@RestController
@Api(description = "图片上传统一接口")
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    /**
     * 文件上传
     *
     * @param dropFile 文件
     * @param request 请求
     * @return
     */
    @PostMapping("upload")
    @ApiOperation(value = "DropZone 图片上传")
    public Map<String, Object> uploadFile(MultipartFile dropFile, MultipartFile[] editorFiles, MultipartFile userFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();

        String imagePath = "";

        // Dropzone 上传
        if (dropFile != null) {
            imagePath = writeFile(dropFile, request);
                    if (imagePath.contains("error")) {
                result.put("status", 500);
                result.put("message", "上传图片失败");
            } else {
                result.put("status", 200);
                result.put("fileName", imagePath);
            }
        }

        // wangEditor 上传
        if (editorFiles != null && editorFiles.length > 0) {
            List<String> imagePaths = new ArrayList<>();

            for (MultipartFile editorFile : editorFiles) {
                imagePath = writeFile(editorFile, request);
                if (imagePath.contains("error")) {
                    result.put("errno", 1);
                    return result;
                }
                imagePaths.add(imagePath);
            }

            result.put("errno", 0);
            result.put("data", imagePaths);
        }

        // 头像上传
        if (userFile != null) {
            imagePath = writeFile(userFile, request);
            if (imagePath.contains("error")) {
                result.put("status", 500);
                result.put("message", "上传图片失败");
            } else {
                result.put("status", 200);
                result.put("imagePath", imagePath);
            }
        }

        return result;
    }

    /**
     * 将图片上传到服务器
     *
     * @param multipartFile
     * @param request
     * @return 返回文件完整路径
     */
    private String writeFile(MultipartFile multipartFile, HttpServletRequest request) {
        // 七牛云上传图片路径地址
        String imagePath = "";

        // 文件保存路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        // 转存文件
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 重命名
            file = new File(filePath, QiniuUtil.renamePic(multipartFile.getOriginalFilename()));
            // 保存至本地
            multipartFile.transferTo(file);
            // 上传到七牛云服务器
            imagePath = QiniuUtil.qiniuUpload(file.getPath());
            // 本地路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagePath;
    }

}
