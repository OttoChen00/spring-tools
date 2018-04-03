package com.smarthome.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OptDateUtil {

    /**获取当前日期
     * @return
     */
    public static String nowDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 获取当前时间戳（10位，秒级）
     *
     * @return
     */
    public static long getNowTimeLong() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 根据字符串时间获取毫秒级时间戳（13位）
     *
     * @param timeStr
     *            字符串日期，例如：2017-01-01 00:00:00
     * @return 例如：1483200000000
     */
    public static long getLongTimeByStr(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time;
        try {
            time = String.valueOf(sdf.parse(timeStr).getTime() / 1000);
            long timel = Long.parseLong(time + "000");
            return timel;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据字符串时间获取秒级时间戳long（10位）
     *
     * @param timeStr
     *            字符串日期，例如：2017-01-01 00:00:00
     * @return 例如：1483200000
     */
    public static long getLTimeByStr(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time;
        try {
            time = String.valueOf(sdf.parse(timeStr).getTime() / 1000);
            long timel = Long.parseLong(time);
            return timel;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将时间戳（10位）转换为时间xxxx-xx-xx xx:xx:xx
     *
     * @param stamp
     * @return
     */
    public static String stampToDate(long stamp) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取当前时间并转成时间格式xxxx-xx-xx xx:xx:xx .
     * 
     * @return 时间：xxxx-xx-xx xx:xx:xx
     */
    public static String getDateFormatString() {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        res = simpleDateFormat.format(date);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(stampToDate(1502539856));
        System.out.println(nowDate());
    }
}
