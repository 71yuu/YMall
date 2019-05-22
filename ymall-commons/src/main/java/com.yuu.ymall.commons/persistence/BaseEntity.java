package com.yuu.ymall.commons.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 *
 * @Classname BaseEntity
 * @Date 2019/5/22 18:09
 * @Created by Yuu
 */
public abstract class BaseEntity implements Serializable {
    /**
     * 创建日期
     */
    private Date created;

    /**
     * 更新日期
     */
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
