package com.xyibq.lanxj.m.forum.common.util;

import java.text.SimpleDateFormat;

public class DataUtil {

    /**
     * @Description 获取当前日期时间，格式yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDataTime() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * @Description 获取当前日期，格式yyyy-MM-dd HH:mm:ss
     */
    public static String getNowData() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * @Description 获取当前时间，格式 HH:mm:ss
     */
    public static String getNowTime() {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
        return fmt.format(System.currentTimeMillis());
    }
}
