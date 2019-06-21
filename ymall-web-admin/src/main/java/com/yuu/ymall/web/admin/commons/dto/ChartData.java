package com.yuu.ymall.web.admin.commons.dto;

import java.io.Serializable;
import java.util.List;

/**
 * EChart 数据传输对象
 *
 * @author by Yuu
 * @classname ChartData
 * @date 2019/6/21 11:00
 */
public class ChartData implements Serializable {

    /**
     * x 轴数据
     */
    private List<Object> xDatas;

    /**
     * y 轴数据
     */
    private List<Object> yDatas;

    /**
     * 总计
     */
    private Object countAll;

    public List<Object> getxDatas() {
        return xDatas;
    }

    public void setxDatas(List<Object> xDatas) {
        this.xDatas = xDatas;
    }

    public List<Object> getyDatas() {
        return yDatas;
    }

    public void setyDatas(List<Object> yDatas) {
        this.yDatas = yDatas;
    }

    public Object getCountAll() {
        return countAll;
    }

    public void setCountAll(Object countAll) {
        this.countAll = countAll;
    }
}
