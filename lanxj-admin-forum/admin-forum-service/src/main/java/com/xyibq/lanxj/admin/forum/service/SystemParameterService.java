package com.xyibq.lanxj.admin.forum.service;

import java.util.Map;

public interface SystemParameterService {

    /**
     * 查询发帖时间限制
     */
    public String queryPostForbiddenTime(String postForbiddenTime);

    /**
     * 设置发帖时间限制
     */
    public void setPostForbiddenTime(Map map);
}
