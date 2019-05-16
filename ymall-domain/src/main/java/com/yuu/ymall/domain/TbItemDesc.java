package com.yuu.ymall.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TbItemDesc {
    private Long itemId;

    private Date created;

    private Date updated;

    private String itemDesc;

}