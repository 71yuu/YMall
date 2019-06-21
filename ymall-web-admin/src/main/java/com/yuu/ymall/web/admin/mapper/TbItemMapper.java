package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbItemMapper extends BaseMapper<TbItem> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 获取商品总数
     *
     * @return
     */
    int getAllItemCount();

    /**
     * 有条件的查询商品集合
     * @param cid 分类 id
     * @return
     */
    List<TbItem> selectItemByCondition(@Param("cid") Long cid, @Param("search") String search);

    /**
     * 根据分类 id 查询商品集合
     *
     * @param params 查询条件
     * @return
     */
    List<TbItem> getItemByCid(Map<String, Object> params);

    /**
     * 查询分类商品总数
     *
     * @param params 查询条件
     * @return
     */
    int getTbItemByCidCount(Map<String, Object> params);

    /**
     * 下架商品
     *
     * @param id 商品 id
     */
    int stopItemById(Long id);

    /**
     * 发布商品
     *
     * @param id 商品 id
     */
    int startItemById(Long id);
}