package com.xyibq.lanxj.m.forum.domain.entity;

import java.util.Date;

/**
 * @author 
 */
public class AttentionPostEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 帖子id
     */
    private Long postId;

    /**
     * 关注状态(0取消关注 1关注)
     */
    private Long attentionStatus;

    /**
     * 修改时间
     */
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getAttentionStatus() {
        return attentionStatus;
    }

    public void setAttentionStatus(Long attentionStatus) {
        this.attentionStatus = attentionStatus;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}