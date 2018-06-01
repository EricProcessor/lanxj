package com.xyibq.lanxj.m.forum.common.util;

public class CheckUtil {

    /**
     * 校验是否为空
     */
    public static boolean checkEmpty(Object obj){
        if(null==obj || "".equals(obj)){
            return true;
        }
        return false;
    }
}
