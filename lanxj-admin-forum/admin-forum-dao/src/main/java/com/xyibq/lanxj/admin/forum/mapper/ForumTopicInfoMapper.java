package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.entity.ForumTopicInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface ForumTopicInfoMapper {

    /**
     * 查询所有版块列表
     */
    public List<ForumTopicInfoEntity> selectAllForumTopicList();

    /**
     * 修改版块信息
     */
    public void updateForumTopicInfo(ForumTopicInfoEntity forumTopicInfoEntity);

    /**
     * 新增版块
     */
    public void insertTopicInfo(ForumTopicInfoEntity forumTopicInfoEntity);



}
