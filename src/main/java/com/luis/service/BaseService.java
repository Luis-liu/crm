package com.luis.service;

import java.util.List;
import java.util.Map;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/19 22:41
 */
public abstract class BaseService<T> {

    /**
     * 新增
     * @param entity
     */
    public abstract Integer add(T entity);

    /**
     * 查询
     * @param entity
     * @return
     */
    public List<T> queryByEntity(T entity) {
        return null;
    }

    /**
     * 查询
     * @param param
     * @return
     */
    public abstract List<T> query(Object param);

    /**
     * 修改
     * @param entity
     */
    public abstract void update(T entity);

    public String format(String format, Object... args) {
        for(int i = 0; i < args.length; i++){
            if(args[i] == null) {
                args[i] = "";
            }
        }

        return String.format(format, args);
    }
}
