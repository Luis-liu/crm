package com.luis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static SimpleDateFormat DEFAULT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat DATE_FROMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDateString(Date date) {
        return getDateString(date, DEFAULT);
    }

    public static String getDateString(Date date, SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public static String getNowString() {
        return DEFAULT.format(new Date());
    }

    public static Date getDate(String date, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }


}
