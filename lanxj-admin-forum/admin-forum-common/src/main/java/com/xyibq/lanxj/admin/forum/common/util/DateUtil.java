package com.xyibq.lanxj.admin.forum.common.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtil {

    /**
     * @Description 获取当前日期时间，格式yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateTime() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * @Description 获取当前日期，格式yyyy-MM-dd
     */
    public static String getNowDate() {
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

    /**
     * @Description 获取当前月
     */
    public static String getNowMonth() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * @param  :请求接口 http://tool.bitefu.net/jiari/vip.php
     * @param httpArg :日期参数yyyyMMdd
     * @return 返回结果
     */
    public static boolean  legalWorkdayJudge(String httpArg) {
        BufferedReader reader = null;
        String result = null;
        JSONObject jsonObjectResult = null;
        StringBuffer sbf = new StringBuffer();
        String httpUrl = "http://api.goseek.cn/Tools/holiday?date=" + httpArg;
        boolean workDayFlag = false;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            jsonObjectResult = JSONObject.parseObject(result);//转为JSONObject对象

            System.out.println(jsonObjectResult);
            //0 上班 1 周末 2 法定假日
            int  workDayF= (int)jsonObjectResult.get("data");
            if(workDayF == 0){
                return  workDayFlag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workDayFlag;
    }

    /**
     * @Description /获取当前月第一天：
     */
    public static String getcurrentDatefirstDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("===============first:"+first);
        return first;

    }


    /**
     * @Description /获取当前月最后一天
     */
    public static String getcurrentDatelastDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:"+last);
        return last;
    }
    //DATE_FORMAT(o.order_time,'%Y-%m-%d')

    public static void main(String[] args) {
        boolean a = new DateUtil().legalWorkdayJudge("20180604");
        System.out.println(a);

        if(a){
            System.out.println("当日为工作日");
        }

        String first = DateUtil.getcurrentDatefirstDate();
        System.out.println(first);
        String last = DateUtil.getcurrentDatelastDate();
        System.out.println(last);
    }
}
