package com.luis.service;

import java.util.List;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/19 22:41
 */
public interface CommonService<T> {

    /**
     * 新增
     * @param entity
     */
    Integer add(T entity);

    /**
     * 查询
     * @param param
     * @return
     */
    List<T> query(Object param);

    /**
     * 修改
     * @param entity
     */
    void update(T entity);
}
