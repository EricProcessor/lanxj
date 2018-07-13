package com.xyibq.lanxj.admin.forum.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SystemParameterMapper {

    /**
     * 查询发帖时间限制
     */
    public String selectPostForbiddenTime(String postForbiddenTime);

    /**
     * 设置发帖时间限制
     */
    public void updatePostForbiddenTime(Map map);
}
