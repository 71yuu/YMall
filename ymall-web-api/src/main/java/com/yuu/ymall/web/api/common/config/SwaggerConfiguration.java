package com.yuu.ymall.web.api.common.config;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname Swagger2Confinguration
 * @Date 2019/5/12 11:33
 * @Created by Yuu
 */
@Configuration  // 让 Spring 来加载该配置类
@EnableWebMvc   // 非 Spring Boot 需启用
@EnableSwagger2 // 启用 Swagger2
@ComponentScan(basePackages = "com.yuu.ymall.web.api.controller")
public class SwaggerConfiguration {

    public static final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Bean
    public Docket createRestApi() {
        log.info("开始加载Swagger2...");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描指定包中的 swagger 注解
                //.apis(RequestHandlerSelectors.basePackage("com.yuu.ymall.controller.admin.controller"))
                // 扫描所有有注解的 api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("YMall API Documentation")
                .description("YMall 商城API接口")
                .termsOfServiceUrl("https://www.71yuu.com")
                .contact(new Contact("Yuu", "https://www.71yuu.com", "1225459207@qq.com"))
                .version("1.0.0")
                .build();
    }
}
