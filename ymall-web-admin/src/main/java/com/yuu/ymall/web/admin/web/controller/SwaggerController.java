package com.yuu.ymall.web.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.List;

/**
 * @Classname SwaggerController
 * @Date 2019/5/20 19:32
 * @Created by Yuu
 */
@RestController
public class SwaggerController {

    @Autowired
    private ApiResourceController apiResourceController;

    /**
     * swagger-resources 路径映射
     *
     * @return
     */
    @GetMapping("/swagger-resources")
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return apiResourceController.swaggerResources();
    }
}
