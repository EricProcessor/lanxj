package com.xyibq.lanxj.admin.forum.domain.vo;

import java.io.Serializable;

public class UserTalentVo implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     *  用户ID
     */
    private Long userId;

    /**
     *  用户名字
     */
    private String userName;


    /**
     *  用户图片url
     */
    private String imageUrl;



    /**
     * 被赞数量
     */
    private Integer answerlikesCount;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 回复数量
     */
    private Integer  answerCount;

    /**
     * 发帖数量
     */
    private Integer  postCount;


    /**
     * 汇总数量
     */
    private Integer  totalCount;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getAnswerlikesCount() {
        return answerlikesCount;
    }

    public void setAnswerlikesCount(Integer answerlikesCount) {
        this.answerlikesCount = answerlikesCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "UserTalentVo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", answerlikesCount=" + answerlikesCount +
                ", commentCount=" + commentCount +
                ", answerCount=" + answerCount +
                ", postCount=" + postCount +
                ", totalCount=" + totalCount +
                '}';
    }
}
