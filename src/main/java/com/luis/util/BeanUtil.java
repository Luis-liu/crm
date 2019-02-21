package com.luis.util;

import com.luis.annotation.SqlField;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Map;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-15 15:24
 **/
public class BeanUtil {

    /**
     * 类型转换
     * @param clazz
     * @param map
     * @param <T>
     * @return
     */
    public static <T> Object fromMapToBean(Class<T> clazz,Map<String,Object> map) {
        try {
            // 创建 对象
            Object obj = clazz.newInstance();
            //变量该对象的字段  
            for (Method method : clazz.getMethods()) {
                SqlField sql = method.getAnnotation(SqlField.class);
                if (sql != null) {
                    //这里可以从注解中获取值
                    String name = sql.value().toUpperCase();
                    if (map.containsKey(name)) {
                        Object value = map.get(name);
                        // 利用set方法赋值
                        if (value instanceof Date) {
                            Date date = (Date) value;
                            method.invoke(obj, date.toLocalDate());
                        } else {
                            method.invoke(obj, value);
                        }
                    }
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}