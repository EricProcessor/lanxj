package com.xyibq.lanxj.admin.forum.service.impl;

import com.xyibq.lanxj.admin.forum.domain.entity.UserTopicAuthEntity;
import com.xyibq.lanxj.admin.forum.mapper.UserTopicAuthMapper;
import com.xyibq.lanxj.admin.forum.service.UserTopicAuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserTopicAuthServiceImpl implements UserTopicAuthService {

    @Resource
    UserTopicAuthMapper userTopicAuthMapper;


    /**
     * 用户分配版块权限
     */
    @Transactional
    public void insertUserTopicAuth(Map<String,Object> map){
        userTopicAuthMapper.insertUserTopicAuth(map);
    }

    /**
     * 版主权限更新
     */
    @Transactional
    public void updateUserTopicAuth(Map<String,Object> map){
        userTopicAuthMapper.updateUserTopicAuth(map);
    }

}
