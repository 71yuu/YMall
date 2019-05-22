package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统基本信息实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbBase extends BaseEntity {
    /**
     * 系统基本信息 id
     */
    private Integer id;

    /**
     * 系统名称
     */
    private String webName;

    /**
     * 系统关键字
     */
    private String keyWord;

    /**
     * 系统描述
     */
    private String description;

    /**
     * 系统路径
     */
    private String sourcePath;

    /**
     * 系统上传路径
     */
    private String uploadPath;

    /**
     * 系统版权
     */
    private String copyright;

    /**
     * 系统数量代码
     */
    private String countCode;

    /**
     * 是否有日志通知
     */
    private Integer hasLogNotice;

    /**
     * 系统日志通知消息
     */
    private String logNotice;

    /**
     * 系统通知
     */
    private Integer hasAllNotice;

    /**
     * 所有通知
     */
    private String allNotice;

    /**
     * 通知
     */
    private String notice;

    /**
     * 更新日志
     */
    private String updateLog;

    /**
     * 前台地址
     */
    private String frontUrl;

}