package com.yuu.ymall.web.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 首页板块展示对象
 *
 * @author by Yuu
 * @classname TbPanelDto
 * @date 2019/7/2 13:57
 */
public class TbPanelDto implements Serializable {

    /**
     * 板块 id
     */
    private Integer id;

    /**
     * 板块名称
     */
    private String name;

    /**
     * 板块类型 0 轮播图 1 板块种类一 2 板块种类二 3 板块种类三
     */
    private Integer type;

    /**
     * 板块内容集合
     */
    private List<TbPanelContentDto> panelContentDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<TbPanelContentDto> getPanelContentDtos() {
        return panelContentDtos;
    }

    public void setPanelContentDtos(List<TbPanelContentDto> panelContentDtos) {
        this.panelContentDtos = panelContentDtos;
    }
}
