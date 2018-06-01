package com.xyibq.lanxj.admin.forum.common.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultUtil {

    /**
     * 成功返回
     */
    public static String successMsg() {
        return resultMsg("0", "操作成功!");
    }

    /**
     * 成功返回
     */
    public static String successMsg(Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", obj);
        return JSON.toJSONString(map);
    }

    /**
     * 失败返回
     */
    public static String errorMsg() {
        return resultMsg("-1", "操作失败!");
    }

    /**
     * 失败提示
     */
    public static String errorMsg(String str) {
        return resultMsg("-1", str);
    }

    /**
     * 返回信息
     */
    static String resultMsg(String code, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        return JSON.toJSONString(map);
    }
}
