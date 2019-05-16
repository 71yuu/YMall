package com.yuu.ymall.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TbItem {
    private Long id;

    private String title;

    private String sellPoint;

    private BigDecimal price;

    private Integer num;

    private Integer limitNum;

    private String image;

    private Long cid;

    private Integer status;

    private Date created;

    private Date updated;

}