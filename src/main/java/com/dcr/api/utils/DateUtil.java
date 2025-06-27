package com.dcr.api.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtil {

    public static String format(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public static Date parse(String pattern, String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(date);
    }

    public static String parseStringToString(String pattern, String date, String mask) throws ParseException {
        if (date.length() <= 0) {
            return "";
        }
        Date dt = DateUtil.parse(pattern, date);
        SimpleDateFormat dateFormat = new SimpleDateFormat(mask);
        return dateFormat.format(dt);
    }
    
}