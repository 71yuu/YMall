package com.yuu.ymall.web.admin.commons.annotation;

import java.lang.annotation.*;

/**
 * 系统级别 Controller 层自定义注解，拦截 Controller
 *
 * @Classname SystemControllerLog
 * @Date 2019/5/12 22:57
 * @Created by Yuu
 */
@Target({ElementType.PARAMETER, ElementType.METHOD}) // 作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时存在，可以通过反射读取
@Documented // 注解应该被 javadoc 工具记录，可以不加
public @interface SystemControllerLog {
    String description() default "";
}
