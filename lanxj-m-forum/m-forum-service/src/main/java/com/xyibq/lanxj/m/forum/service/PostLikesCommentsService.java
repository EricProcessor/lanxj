package com.xyibq.lanxj.m.forum.service;

import com.xyibq.lanxj.m.forum.domain.entity.PostCommentsRelateEntity;
import com.xyibq.lanxj.m.forum.domain.entity.PostLikesRelateEntity;

/**
 * 帖子点赞、评论服务
 */
public interface PostLikesCommentsService {

    /**
     * 帖子新增点赞
     */
    public void insertPostLike(PostLikesRelateEntity postLikesRelateEntity) throws Exception;

    /**
     * 帖子取消点赞
     */
    public int cancelPostToNoLike(PostLikesRelateEntity postLikesRelateEntity) throws Exception;

    /**
     * 帖子新增评论
     */
    public void insertPostComment(PostCommentsRelateEntity postCommentsRelateEntity) throws Exception;

    /**
     * 用户删除帖子评论
     */
    public int deletePostComment(PostCommentsRelateEntity postCommentsRelateEntity) throws Exception;

    /**
     * 帖子新增点赞包含MQ发送（业务可以事务控制）
     */
    public void insertPostLikeMq(PostLikesRelateEntity postLikesRelateEntity) throws Exception;

    /**
     * 帖子新增评论包含MQ发送（业务可以事务控制）
     */
    public void insertPostCommentMq(PostCommentsRelateEntity postCommentsRelateEntity) throws Exception;
}
