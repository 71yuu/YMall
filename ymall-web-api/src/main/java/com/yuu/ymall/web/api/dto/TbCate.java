package com.yuu.ymall.web.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author by Yuu
 * @classname TbCate
 * @date 2019/7/2 10:23
 */
public class TbCate implements Serializable {
    /**
     * 分类 id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类的子分类
     */
    private List<TbCate> catesons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TbCate> getCatesons() {
        return catesons;
    }

    public void setCatesons(List<TbCate> catesons) {
        this.catesons = catesons;
    }
}
