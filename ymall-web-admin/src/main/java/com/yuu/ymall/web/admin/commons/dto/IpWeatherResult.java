package com.yuu.ymall.web.admin.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname IpWeatherResult
 * @Date 2019/5/13 19:42
 * @Created by Yuu
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpWeatherResult implements Serializable {

    /**
     * 消息
     */
    String msg;

    /**
     * 返回数据
     */
    List<City> result;
}
