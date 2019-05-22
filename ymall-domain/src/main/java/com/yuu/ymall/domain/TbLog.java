package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import com.yuu.ymall.commons.utils.MapperUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统日志实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbLog extends BaseEntity {
    /**
     * 日志 id
     */
    private Integer id;

    /**
     * 日志名称
     */
    private String name;

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求用户
     */
    private String user;

    /**
     * 请求 ip
     */
    private String ip;

    /**
     * 请求 ip 信息
     */
    private String ipInfo;

    /**
     * 请求时间
     */
    private Integer time;

    /**
     * 设置请求参数，屏蔽密码
     *
     * @param paramMap 参数 Map
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
        if (paramMap == null) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            String key = param.getKey();
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            String obj = StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "你是看不见我的" : paramValue;
            params.put(key, obj);
        }
        this.requestParam = MapperUtil.mapToJson(params);
    }

}