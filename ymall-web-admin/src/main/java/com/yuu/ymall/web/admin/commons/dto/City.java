package com.yuu.ymall.web.admin.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @Classname City
 * @Date 2019/5/13 19:43
 * @Created by Yuu
 */
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrct() {
        return distrct;
    }

    public void setDistrct(String distrct) {
        this.distrct = distrct;
    }
}
