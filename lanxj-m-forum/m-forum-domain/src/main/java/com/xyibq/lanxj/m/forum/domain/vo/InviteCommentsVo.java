package com.xyibq.lanxj.m.forum.domain.vo;

import java.io.Serializable;

public class InviteCommentsVo implements Serializable{


//    `id` bigint(20) NOT NULL,
//  `user_id` bigint(20) DEFAULT NULL COMMENT '发帖人ID',
//            `user_name` varchar(60) DEFAULT NULL COMMENT '发帖人姓名',
//            `user_type` bigint(10) DEFAULT NULL COMMENT '用户类型（0普通用户 1管理员 2 子管理员）',
//            `position` varchar(120) DEFAULT NULL COMMENT '员工岗位',
//            `invite_comment_auth` bigint(10) DEFAULT NULL COMMENT '邀请评论权限(0无 1有)',
//            `remark` varchar(60) DEFAULT NULL COMMENT '备注',
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 发帖人ID
     */
    private Long userId;

    /**
     * 发帖人姓名
     */
    private String userName;

    /**
     *用户类型（0普通用户 1管理员 2 子管理员）
     */
    private Long userType;

    /**
     *'员工岗位'
     */
    private String position;

    /**
     *邀请评论权限(0无 1有)
     */
    private Long inviteCommentAuth;

    /**
     *备注
     */
   // private String remark;

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

    /*public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }*/

    @Override
    public String toString() {
        return "InviteCommentsVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", position='" + position + '\'' +
                ", inviteCommentAuth=" + inviteCommentAuth +
                '}';
    }
}
