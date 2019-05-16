package com.yuu.ymall.web.admin.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname City
 * @Date 2019/5/13 19:43
 * @Created by Yuu
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable {

    /**
     * 城市
     */
    String city;

    /**
     * 区域
     */
    String distrct;
}
