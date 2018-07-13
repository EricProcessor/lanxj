package com.xyibq.lanxj.admin.forum.service;


import com.xyibq.lanxj.admin.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.admin.forum.domain.entity.ForumTopicInfoEntity;
import com.xyibq.lanxj.admin.forum.domain.vo.ForumPostInfoDetailVo;

import java.util.List;

/**
 * 论坛帖子信息服务
 */
public interface ForumPostInfoService {

    /**
     * 查询版块帖子列表
     */
    public List<ForumPostInfoEntity> queryTopicPostList(String topic);

    /**
     * 查询帖子详情
     */
    public ForumPostInfoDetailVo queryPostDetail(String postId)  throws Exception;

    /**
     * 将帖子置顶
     */
    public void setPostToTop(String postId);

    /**
     * 取消帖子置顶
     */
    public void cancelPostTop(String postId);

    /**
     * 删除帖子
     */
    public void removePostInfoByAdmin(String postId);

    /**
     * 查询帖子置顶日期  截至到当日的帖子
     */
    public List<String> queryTodayWillCancelPostList();

    /**
     * 审核帖子
     */
    public int modifyPostInfoBypostId(String postId);

    /**
     * 查询帖子详情（不包含点赞及评论，单表查询）
     */
    public ForumPostInfoEntity queryPostInfoDetailByPostId(String postId) throws Exception;

    /**
     * 数据统计——版块评论前五名
     */
    public List<ForumTopicInfoEntity> queryTopCommentCount(String topicId);

    /**
     * 数据统计——版块点赞前五名
     */
    public List<ForumTopicInfoEntity> queryTopLikeCount(String topicId);

    /**
     * 数据统计——版块评论前五名
     */
    public List<ForumTopicInfoEntity> queryTopPVCount(String topicId);

    /**
     * 统计当前月份 用户所有的发帖
     */
    public List<ForumPostInfoEntity> queryUserSendPostbyuserIdandDate(String userId);
}
