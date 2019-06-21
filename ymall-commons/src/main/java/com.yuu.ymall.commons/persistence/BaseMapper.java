package com.yuu.ymall.commons.persistence;


import java.util.List;

/**
 * 所有数据访问层的基类
 *
 * @Classname BaseMapper
 * @Date 2019/5/22 11:14
 * @Created by Yuu
 */
public interface BaseMapper<T> {

    /**
     * 新增
     *
     * @param record 对象
     * @return
     */
    int insert(T record);

    /**
     * 根据主键 id 查询
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据主键 id 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
}
