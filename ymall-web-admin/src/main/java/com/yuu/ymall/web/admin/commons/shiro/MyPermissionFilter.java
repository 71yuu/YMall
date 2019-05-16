package com.yuu.ymall.web.admin.commons.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname MyPermissionFilter
 * @Date 2019/5/11 19:20
 * @Created by Yuu
 */
public class MyPermissionFilter extends AuthorizationFilter {

    private static final Logger log = LoggerFactory.getLogger(MyPermissionFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {

        Subject subject = this.getSubject(request, response);
        String[] perms = (String[]) o;
        boolean isPermmitted = true;

        if (subject.getPrincipal() == null) {
            if (FilterUtil.isAjax(request)) {
                log.info("未登录或登录时间过长，是 Ajax！");
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("success", false);
                resultMap.put("message", "您还未登录或登录时间过长，请重新登录！");
                FilterUtil.out(response, resultMap);
            } else {
                log.info("未登录或登录时间过长，不是 Ajax！");
                this.saveRequestAndRedirectToLogin(request, response);
            }
        } else {
            if (perms != null && perms.length > 0) {
                if (perms.length == 1) {
                    if (!subject.isPermitted(perms[0])) {
                        isPermmitted = false;
                    }
                } else if (!subject.isPermittedAll(perms)) {
                    isPermmitted = false;
                }
            }
            if (!isPermmitted) {
                if (FilterUtil.isAjax(request)) {
                    log.info("没有该权限，并且是 Ajax 请求");
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("success", false);
                    resultMap.put("message", "抱歉，您没有该权限！看就看，你点它干什么...");
                    FilterUtil.out(response, resultMap);
                }
            } else {
                return isPermmitted;
            }
        }
        return isPermmitted;
    }
}
