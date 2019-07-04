package com.yuu.ymall.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品实体
 */
@Data
public class TbItem implements Serializable {
    /**
     * 商品 id
     */
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer num;

    /**
     * 一次最多购买多少件
     */
    private Integer limitNum;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品分类 id
     */
    private Long cid;

    /**
     * 商品状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 获取图片地址数组
     *
     * @return
     */
    public String[] getImages() {
        if (!StringUtils.isBlank(this.image)) {
            String[] images = this.image.split(",");
            return images;
        }
        return null;
    }

}
