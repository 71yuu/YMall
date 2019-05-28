package com.yuu.ymall.web.admin.commons.dto;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 分页展示对象
 *
 * @Classname PageInfo
 * @Date 2019/5/16 23:53
 * @Created by Yuu
 */
@Setter
@Getter
public class DataTablesResult<T> implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;
    private int start = 0;
    private int length = 10;
    private int pageNum = 1;

    /**
     * 初始化 DataTables 数据
     * @param request
     */
    public DataTablesResult(HttpServletRequest request) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        this.draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        this.start = strStart == null ? 0 : Integer.parseInt(strStart);
        this.length = strLength == null ? 10 : Integer.parseInt(strLength);

        // 计算页面
        this.pageNum = start/length + 1;
    }

}
