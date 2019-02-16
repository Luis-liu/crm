package com.luis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getDateString(Date date) {
        return sdf.format(date);
    }

    public static String getNowString() {
        return sdf.format(new Date());
    }

}
