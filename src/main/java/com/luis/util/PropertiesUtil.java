package com.luis.util;

import java.util.ResourceBundle;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-15 14:13
 **/
public class PropertiesUtil {

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle("/config.properties");
    }

    private PropertiesUtil() {}

    /**
     * 获取配置文件
     * @param key
     * @return
     */
    public static String getProperties(String key) {
        return resourceBundle.getString(key);
    }

}
