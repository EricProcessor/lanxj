package com.xyibq.lanxj.m.forum.domain.vo;

import java.io.Serializable;

public class MyMessageVo implements Serializable{

    private static final long serialVersionUID = 1L;

    //未来涉及用户的图片 部门需要增加

    /**
     *  用户ID
     */
    private Long userId;

    /**
     *  用户名字
     */
    private String userName;

    /**
     *  员工岗位
     */
    private String position;


    /**
     *  发帖数量
     */
    private int postcount;

    /**
     *  评论数量
     */
    private int sendCommentcount;

    /**
     *  被赞数量
     */
    private int postlikecount;

    /**
     *  我的未读消息数量
     */
    private int myunreadmessagecount;

    public int getMyunreadmessagecount() {
        return myunreadmessagecount;
    }

    public void setMyunreadmessagecount(int myunreadmessagecount) {
        this.myunreadmessagecount = myunreadmessagecount;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPostcount() {
        return postcount;
    }

    public void setPostcount(int postcount) {
        this.postcount = postcount;
    }

    public int getSendCommentcount() {
        return sendCommentcount;
    }

    public void setSendCommentcount(int sendCommentcount) {
        this.sendCommentcount = sendCommentcount;
    }

    public int getPostlikecount() {
        return postlikecount;
    }

    public void setPostlikecount(int postlikecount) {
        this.postlikecount = postlikecount;
    }
}
