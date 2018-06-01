package com.xyibq.lanxj.admin.forum.service;

import java.util.Map;

public interface UserTopicAuthService {

    /**
     * 用户分配版块权限
     */
    public void insertUserTopicAuth(Map<String,Object> map);

    /**
     * 版主权限更新
     */
    public void updateUserTopicAuth(Map<String,Object> map);
}
