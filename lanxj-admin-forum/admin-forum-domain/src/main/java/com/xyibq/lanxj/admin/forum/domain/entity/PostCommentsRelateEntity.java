package com.xyibq.lanxj.admin.forum.domain.entity;

import java.io.Serializable;

/**
 * 帖子评论关联表
 */
public class PostCommentsRelateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 帖子ID
     */
    private Long postId;

    /**
     * 帖子状态（10待审 20正常 30拒绝 40删除 ）
     */
    private Integer commentStatus;

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
     * 评论时间
     */
    private String commentTime;


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

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
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

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

}