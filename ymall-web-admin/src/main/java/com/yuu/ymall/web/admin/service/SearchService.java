package com.yuu.ymall.web.admin.service;

/**
 * 搜索服务
 *
 * @author by Yuu
 * @classname SearchService
 * @date 2019/7/9 19:48
 */
public interface SearchService {

    /**
     * 同步单个索引
     *
     * @param type 0 更新索引 1 删除索引
     * @param itemId 商品 id
     * @return
     */
    void refreshItem(int type, Long itemId);

    /**
     * 同步所有索引
     *
     * @return
     */
    void importAllItems();
}
