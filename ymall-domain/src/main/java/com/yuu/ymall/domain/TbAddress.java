package com.yuu.ymall.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbAddress implements Serializable {
    private Long addressId;

    private Long userId;

    private String userName;

    private String tel;

    private String streetName;

    private Boolean isDefault;
}