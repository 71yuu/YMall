package com.yuu.ymall.domain;

import com.yuu.ymall.commons.utils.MapperUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class TbLog {
    private Integer id;

    private String name;

    private Integer type;

    private String url;

    private String requestType;

    private String requestParam;

    private String user;

    private String ip;

    private String ipInfo;

    private Integer time;

    private Date createDate;

    /**
     * 设置请求参数
     *
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
        if (paramMap == null) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            String key = param.getKey();
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            String obj = StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "你是看不到我的" : paramValue;
            params.put(key, obj);
        }
        try {
            this.requestParam = MapperUtil.obj2json(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}