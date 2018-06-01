package com.xyibq.lanxj.m.forum.mapper;

import com.xyibq.lanxj.m.forum.domain.entity.ForumTopicInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * (论坛)帖子信息表【forum_topic_info】mapper
 */
@Mapper
public interface ForumTopicInfoMapper {


    /**
     * 查询已开启的版块信息列表
     */
    public List<ForumTopicInfoEntity> selectForumTopicList();

}
