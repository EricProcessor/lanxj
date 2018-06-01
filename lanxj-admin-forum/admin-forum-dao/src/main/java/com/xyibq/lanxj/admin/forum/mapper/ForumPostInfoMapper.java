package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.entity.ForumPostInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ForumPostInfoMapper {

    /**
     * 将帖子置顶
     */
    public void setPostToTop(String postId);

    /**
     * 将帖子置顶
     */
    public void cancelPostTop(String postId);

    /**
     * 查询版块帖子列表
     */
    public List<ForumPostInfoEntity> selectTopicPostList(String topic);

    /**
     * 查询帖子详情（包含点赞及评论）
     */
    public ForumPostInfoEntity selectPostDetail(@Param("postId")String postId);

    /**
     * 删除帖子
     */
    public void deletePostInfo(@Param("postId")String postId);

    /**
     * 查询置顶日期截至到今天的帖子
     */
    public List<String> selectTodayWillCancelPostList();

}
