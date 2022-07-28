package com.zh.oes.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final String dateFormat = "yyyy-MM-dd";

    /**
     * 格式化日期
     *
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * 在日期date上增加amount天 。
     *
     * @param date   处理的日期，非null
     * @param amount 要加的天数，可能为负数
     */
    public static Date addDays(Date date, int amount) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + amount);
        return now.getTime();
    }

    // 查询queryDate是否大于当前日期
    public static boolean compare(String queryDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(queryDate);
            return new Date().compareTo(date) <= 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    // 查询end是否大于begin
    public static boolean compare(String begin, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date beginDate = simpleDateFormat.parse(begin);
            Date endDate = simpleDateFormat.parse(end);
            return beginDate.compareTo(endDate) > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
