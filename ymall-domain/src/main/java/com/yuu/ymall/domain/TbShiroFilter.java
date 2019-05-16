package com.yuu.ymall.domain;

import lombok.Data;

@Data
public class TbShiroFilter {
    private Integer id;

    private String name;

    private String perms;

    private Integer sortOrder;

}