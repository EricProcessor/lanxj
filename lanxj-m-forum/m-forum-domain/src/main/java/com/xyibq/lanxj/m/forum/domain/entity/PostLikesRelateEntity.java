package com.xyibq.lanxj.m.forum.domain.entity;

import java.io.Serializable;

/**
 * 帖子点赞关联表
 */
public class PostLikesRelateEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     *  帖子ID
     */
    private Long postId;

    /**
     * 点赞用户id
     */
    private Long likeUserId;

    /**
     * 点赞状态
     */
    private Long likeStatus;

    /**
     *  点赞用户名称
     */
    private String likeUserName;

    /**
     * 点赞时间
     */
    private String likeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Long likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Long getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(Long likeUserId) {
        this.likeUserId = likeUserId;
    }

    public String getLikeUserName() {
        return likeUserName;
    }

    public void setLikeUserName(String likeUserName) {
        this.likeUserName = likeUserName;
    }

    public String getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(String likeTime) {
        this.likeTime = likeTime;
    }

}
