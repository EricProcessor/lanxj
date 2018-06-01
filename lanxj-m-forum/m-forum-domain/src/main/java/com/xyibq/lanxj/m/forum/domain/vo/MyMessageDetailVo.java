package com.xyibq.lanxj.m.forum.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class MyMessageDetailVo implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 消息ID
     */
    private Long msgId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 是否已读
     */
    private Long readYn;

    /**
     * 帖子ID
     */
    private Long postId;





    /**
     * 帖子前缀内容（帖子前十个字）
     */
    private String postPrefixContext;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReadYn() {
        return readYn;
    }

    public void setReadYn(Long readYn) {
        this.readYn = readYn;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostPrefixContext() {
        return postPrefixContext;
    }

    public void setPostPrefixContext(String postPrefixContext) {
        this.postPrefixContext = postPrefixContext;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
