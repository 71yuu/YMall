package com.yuu.ymall.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TbOrderItem {
    private String id;

    private String itemId;

    private String orderId;

    private Integer num;

    private String title;

    private BigDecimal price;

    private BigDecimal totalFee;

    private String picPath;

    private Integer total;

}