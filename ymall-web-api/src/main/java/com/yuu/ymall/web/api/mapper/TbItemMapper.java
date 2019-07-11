package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    TbItem selectByPrimaryKey(Long id);

    List<TbItem> selectAll();

    int updateByPrimaryKey(TbItem record);

    /**
     * 商品减少库存
     *
     * @param productId 商品 id
     * @param num 商品数量
     * @return
     */
    int reduceItemNum(@Param("productId") Long productId, @Param("num") int num);

    /**
     * 商品库存 +1
     *
     * @param productId 商品 id
     * @param num 商品数量
     * @return
     */
    int addItemNum(@Param("productId") String productId, @Param("num") int num);
}
