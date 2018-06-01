package com.xyibq.lanxj.m.forum.mapper;

import com.xyibq.lanxj.m.forum.domain.entity.ForumPostInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 版块信息表【forum_post_info】 mapper
 */
@Mapper
public interface ForumPostInfoMapper {

    /**
     * 获取最大psot_id
     */
    public int selectForumPostInfoMaxId();

    /**
     * 新增帖子信息
     */
    public void insertPostInfo(ForumPostInfoEntity forumPostInfoEntity);

    /**
     * 根据版块查询置顶帖子列表
     */
    public List<ForumPostInfoEntity> selectPostListByTopicId(ForumPostInfoEntity forumPostInfoEntity);


    /**
     * 查询帖子详情（包含点赞及评论）
     */
    public ForumPostInfoEntity selectPostInfoByPostId(@Param(value="postId") Long postId);

    /**
     * 员工动态列表
     */
   public List<ForumPostInfoEntity> selectPostListByUserId(@Param("userId") Long userId);

    /**
     * 删除帖子
     */
    public void deletePostInfo(@Param(value="postId") Long postId);


}
