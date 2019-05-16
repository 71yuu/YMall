package com.yuu.ymall.web.admin.commons.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Classname FilterUtil
 * @Date 2019/5/11 19:23
 * @Created by Yuu
 */
public class FilterUtil {

    private static final Logger log = LoggerFactory.getLogger(FilterUtil.class);

    /**
     * 是否是 Ajax 请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest)request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return true;
        }
        return false;
    }

    /**
     * 使用 response 输出 JSON
     *
     * @param response
     * @param resultMap
     */
    public static void out(ServletResponse response, Map<String, Object> resultMap) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
            out.println(json);
        } catch (IOException e) {
            log.error(e + "输出 JSON 出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
