package com.wondersgroup.datareport.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.utils
 * @authorName:wangjiaming
 * @createDate:2018-02-26
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class DateUtil {
    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();

    public static Date parse(String str) throws Exception {
        SimpleDateFormat sdf = local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            local.set(sdf);
        }
        return sdf.parse(str);
    }

    public static String format(Date date) throws Exception {
        SimpleDateFormat sdf = local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            local.set(sdf);
        }
        return sdf.format(date);
    }
    public static String format(Date date,String format) throws Exception {
        SimpleDateFormat sdf = local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(format, Locale.US);
            local.set(sdf);
        }
        return sdf.format(date);
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
}
