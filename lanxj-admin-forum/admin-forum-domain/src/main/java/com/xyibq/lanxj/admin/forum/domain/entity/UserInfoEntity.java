package com.xyibq.lanxj.admin.forum.domain.entity;

import java.io.Serializable;

/**
 * 用户信息表
 */
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *  主键
     */
    private Long id;

    /**
     *  用户ID
     */
    private Long userId;

    /**
     *  用户名字
     */
    private String userName;

    /**
     *  用户类型（0普通用户 1管理员 2 子管理员）
     */
    private Long userType;

    /**
     *  员工岗位
     */
    private String position;

    /**
     *  邀请评论权限(0无 1有）
     */
    private Long inviteCommentAuth;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getInviteCommentAuth() {
        return inviteCommentAuth;
    }

    public void setInviteCommentAuth(Long inviteCommentAuth) {
        this.inviteCommentAuth = inviteCommentAuth;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", position='" + position + '\'' +
                ", inviteCommentAuth=" + inviteCommentAuth +
              //  ", remark='" + remark + '\'' +
                '}';
    }
}