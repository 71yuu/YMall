package com.yuu.ymall.web.admin.commons.utils;

import com.google.common.collect.Maps;
import com.yuu.ymall.commons.utils.MapperUtil;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ObjectUtil
 * @Date 2019/5/13 16:26
 * @Created by Yuu
 */
public class ObjectUtil {

    /**
     * Bean 转 Map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    public static String mapToStringAll(Map<String, String[]> paramMap) throws Exception {
        if (paramMap == null) {
            return "";
        }
        Map<String, Object> params = new HashMap<>(16);
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            String key = param.getKey();
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.put(key, paramValue);
        }
        return MapperUtil.obj2json(params);
    }
}
