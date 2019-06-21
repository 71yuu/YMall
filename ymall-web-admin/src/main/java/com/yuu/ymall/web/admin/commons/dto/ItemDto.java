package com.yuu.ymall.web.admin.commons.dto;

import com.yuu.ymall.domain.TbItem;
import lombok.Data;

/**
 * @Classname ItemDto
 * @Date 2019/6/5 23:19
 * @Created by Yuu
 */
@Data
public class ItemDto extends TbItem {
    /**
     * 商品描述
     */
    private String detail;

    /**
     * 商品分类名称
     */
    private String cname;
}
