package com.yuu.ymall.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TbThanks {
    private Integer id;

    private String nickName;

    private String username;

    private BigDecimal money;

    private String info;

    private String email;

    private Integer state;

    private String payType;

    private String orderId;

    private Date date;

}