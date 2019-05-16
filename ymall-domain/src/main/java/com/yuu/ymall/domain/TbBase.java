package com.yuu.ymall.domain;

import lombok.Data;

@Data
public class TbBase {
    private Integer id;

    private String webName;

    private String keyWord;

    private String description;

    private String sourcePath;

    private String uploadPath;

    private String copyright;

    private String countCode;

    private Integer hasLogNotice;

    private String logNotice;

    private Integer hasAllNotice;

    private String allNotice;

    private String notice;

    private String updateLog;

    private String frontUrl;

}