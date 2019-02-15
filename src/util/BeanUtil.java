package util;

import annotation.SqlField;

import java.lang.reflect.Field;
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

    public static <T> Object fromMapToBean(Class<T> clazz,Map<String,Object> map) {
        try {
            // 获取类属性
            // 创建 对象
            Object obj = clazz.newInstance();
            //变量该对象的字段  
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                SqlField sql = field.getAnnotation(SqlField.class);
                if (sql != null) {
                    //这里可以从注解中获取值
                    String value1 = sql.value();
                    String name = value1;
                    if (map.containsKey(name)) {
                        Object value = map.get(name);
                        Object[] args = new Object[1];
                        args[0] = value;

                        //写入obj
                        field.set(obj, value);
                        return obj;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}