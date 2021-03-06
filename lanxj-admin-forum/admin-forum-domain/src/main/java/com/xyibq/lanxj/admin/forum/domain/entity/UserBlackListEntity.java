package com.xyibq.lanxj.admin.forum.domain.entity;

import java.io.Serializable;

/**
 * 用户黑名单信息表
 */
public class UserBlackListEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 发帖人ID
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 备注
     */
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}