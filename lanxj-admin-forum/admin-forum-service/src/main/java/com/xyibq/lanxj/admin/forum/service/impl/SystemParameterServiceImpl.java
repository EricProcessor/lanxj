package com.xyibq.lanxj.admin.forum.service.impl;

import com.xyibq.lanxj.admin.forum.mapper.SystemParameterMapper;
import com.xyibq.lanxj.admin.forum.service.SystemParameterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class SystemParameterServiceImpl implements SystemParameterService {

    @Resource
    SystemParameterMapper systemParameterMapper;

    /**
     * 查询发帖时间限制
     */
    public String queryPostForbiddenTime(String postForbiddenTime){
        return systemParameterMapper.selectPostForbiddenTime(postForbiddenTime);
    }

    /**
     * 设置发帖时间限制
     */
    public void setPostForbiddenTime(Map map){
        systemParameterMapper.updatePostForbiddenTime(map);
    }
}
