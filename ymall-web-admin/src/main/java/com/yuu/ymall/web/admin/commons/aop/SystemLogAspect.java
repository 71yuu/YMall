package com.yuu.ymall.web.admin.commons.aop;

import com.yuu.ymall.domain.TbLog;
import com.yuu.ymall.web.admin.commons.annotation.SystemControllerLog;
import com.yuu.ymall.web.admin.service.SystemService;
import com.yuu.ymall.web.admin.commons.utils.IPInfoUtil;
import com.yuu.ymall.web.admin.commons.utils.ThreadPoolUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Spring AOP 实现日志管理
 *
 * @Classname SystemLogAspect
 * @Date 2019/5/12 23:18
 * @Created by Yuu
 */
@Aspect
@Component
public class SystemLogAspect {

    private Logger log = LoggerFactory.getLogger(SystemLogAspect.class);

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal beginTime");

    @Autowired
    private SystemService systemService;

    @Autowired(required = false)
    private HttpServletRequest request;

    /**
     * Controller 层切点，注解方式
     */
    @Pointcut("@annotation(com.yuu.ymall.web.admin.commons.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知（在方法执行之前返回）用于拦截 Controller 层记录用户的操作开始时间
     *
     * @param joinPoint 切点
     * @return
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
        // 线程绑定变量（该数据只有当前请求的线程可见）
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);
    }

    /**
     * 后置通知（在方法执行之后返回）用于拦截 Controller 层操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        try {
            String username = SecurityUtils.getSubject().getPrincipal().toString();
            if (username != null) {
                TbLog tbLog = new TbLog();
                // 日志标题
                tbLog.setName(getControllerMethodDescription(joinPoint));
                // 日志类型
                tbLog.setType(0);
                // 日志请求 url
                tbLog.setUrl(request.getRequestURI());
                // 请求方式
                tbLog.setRequestType(request.getMethod());
                // 请求参数
                Map<String, String[]> logParams = request.getParameterMap();
                tbLog.setMapToParams(logParams);
                // 请求用户
                tbLog.setUser(username);
                // 请求 IP
                tbLog.setIp(IPInfoUtil.getIpAddr(request));
                // IP 地址
                // tbLog.setIpInfo(IPInfoUtil.getIpCity(tbLog.getIp()));
                // todo
                tbLog.setIpInfo(IPInfoUtil.getIpCity("58.23.36.251"));
                // 请求开始时间
                Date logStartTime = beginTimeThreadLocal.get();

                long beginTime = beginTimeThreadLocal.get().getTime();
                long endTime = System.currentTimeMillis();

                // 请求耗时
                Long logElapsedTime = endTime - beginTime;
                tbLog.setTime(logElapsedTime.intValue());
                tbLog.setCreateDate(logStartTime);

                // 调用线程保存至数据库
                ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(tbLog, systemService));
            }
        } catch (Exception e) {
            log.error("AOP 后置通知异常", e);
        }
    }

    /**
     * 保存日志
     */
    private static class SaveSystemLogThread implements Runnable {
        private TbLog tbLog;
        private SystemService systemService;

        public SaveSystemLogThread(TbLog tbLog, SystemService systemService) {
            this.tbLog = tbLog;
            this.systemService = systemService;
        }

        @Override
        public void run() {
            systemService.addLog(tbLog);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于 Controller 层注解
     *
     * @param joinPoint 切点
     * @return  方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        // 获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取相关参数
        Object[] arguments = joinPoint.getArgs();
        // 生成类对象
        Class targetClass = Class.forName(targetName);
        // 获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String description = "";

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            // 比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载
            if (clazzs.length != arguments.length) {
                continue;
            }
            description = method.getAnnotation(SystemControllerLog.class).description();
        }
        return description;
    }
}
