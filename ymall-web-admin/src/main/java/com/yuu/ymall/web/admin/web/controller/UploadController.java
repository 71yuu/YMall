package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.admin.commons.utils.QiniuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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
     * DropZone 图片上传
     * @param dropzFile 文件
     * @param request 请求
     * @return
     */
    @PostMapping("upload")
    @ApiOperation(value = "DropZone 图片上传")
    public BaseResult uploadFile(MultipartFile dropzFile, HttpServletRequest request) {

        String imagePath = null;
        // 文件保存路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        // 转存文件
        try {
            // 保存至服务器
            File file = new File((filePath));
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(filePath, QiniuUtil.renamePic(dropzFile.getOriginalFilename()));
            dropzFile.transferTo(file);
            // 上传到七牛云服务器
            imagePath = QiniuUtil.qiniuUpload(file.getPath());
            if (imagePath.contains("error")) {
                BaseResult.fail("上传图片失败");
            }
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BaseResult.success("上传图片成功", imagePath);
    }

}
