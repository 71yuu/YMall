package com.yuu.ymall.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TbExpress {
    private Integer id;

    private String expressName;

    private Integer sortOrder;

    private Date created;

    private Date updated;

}