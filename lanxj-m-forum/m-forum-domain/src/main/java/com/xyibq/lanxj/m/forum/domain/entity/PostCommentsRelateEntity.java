package com.xyibq.lanxj.m.forum.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子评论关联表
 */
public class PostCommentsRelateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 帖子ID
     */
    private Long postId;

    /**
     * 评论人ID
     */
    private Long commentUserId;

    /**
     * 评论人名称
     */
    private String commentUserName;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论状态（0无效 1有效 ）
     */
    private Long commentStatus;

    /**
     * 评论时间
     */
    private Date commentTime;

    /**
     * 删除人
     */
    private Long deleteUser;

    /**
     * 删除时间
     */
    private Date deleteTime;


    /**
     * 是否邀请评论（0否 1是）
     */
    private Long inviteCommentsYn;

    public Long getInviteCommentsYn() {
        return inviteCommentsYn;
    }

    public void setInviteCommentsYn(Long inviteCommentsYn) {
        this.inviteCommentsYn = inviteCommentsYn;
    }

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

    public Long getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Long commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Long commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Long getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(Long deleteUser) {
        this.deleteUser = deleteUser;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

}