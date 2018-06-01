package com.xyibq.lanxj.admin.forum.domain.entity;

import java.io.Serializable;

public class ForumPostInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子id
     */
    private Long postId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * (所属)板块id
     */
    private Long topicId;

    /**
     * 帖子状态（0草稿 10待审 20拒绝 30正常 40删除 ）
     */
    private Integer postStatus;

    /**
     * 是否匿名
     */
    private Integer anonymityYn;

    /**
     * 是否置顶
     */
    private Integer topYn;

    /**
     * 点赞数量
     */
    private Integer likesCount;

    /**
     * 评论数量
     */
    private Integer commentsCount;

    /**
     * 浏览量
     */
    private Integer pageView;

    /**
     * 帖子内容
     */
    private String postContent;

    /**
     * 拒绝原因
     */
    private String refuseReason;

    /**
     * 发帖时间
     */
    private String createTime;

    /**
     * 删帖时间
     */
    private String deleteTime;

    /**
     * 开始置顶时间
     */
    private String topStartTime;

    /**
     * 置顶有效时间
     */
    private String topEndTime;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Integer getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }

    public Integer getAnonymityYn() {
        return anonymityYn;
    }

    public void setAnonymityYn(Integer anonymityYn) {
        this.anonymityYn = anonymityYn;
    }

    public Integer getTopYn() {
        return topYn;
    }

    public void setTopYn(Integer topYn) {
        this.topYn = topYn;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getTopStartTime() {
        return topStartTime;
    }

    public void setTopStartTime(String topStartTime) {
        this.topStartTime = topStartTime;
    }

    public String getTopEndTime() {
        return topEndTime;
    }

    public void setTopEndTime(String topEndTime) {
        this.topEndTime = topEndTime;
    }
}
