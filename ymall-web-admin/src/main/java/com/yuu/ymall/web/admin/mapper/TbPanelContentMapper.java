package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbPanelContent;

import java.util.List;
import java.util.Map;

public interface TbPanelContentMapper extends BaseMapper<TbPanelContent> {

    /**
     * 根据主键 id 删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *  添加查询板块内容
     * @param params 参数
     * @return
     */
    List<TbPanelContent> getTbPanelContentByPanelId(Map<String, Object> params);


    /**
     * 条件查询板块内容总数目
     * @param params 查询参数
     * @return
     */
    int getTbPanelContentCount(Map<String, Object> params);

    /**
     * 根据商品 ID 查询商品是否关联首页内容
     * @param id 商品 id
     * @return
     */
    int selectContentByIid(Long id);
}