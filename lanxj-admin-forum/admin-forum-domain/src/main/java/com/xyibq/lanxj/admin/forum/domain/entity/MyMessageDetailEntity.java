package com.xyibq.lanxj.admin.forum.domain.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class MyMessageDetailEntity implements Serializable {
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
     * 发帖人ID
     */
    private Long postuserId;

    /**
     * 发帖人用户姓名
     */
    private String postuserName;

    /**
     * 关注帖子ID的用户ID
     */
    private Long attentuserId;
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

    public Long getPostuserId() {
        return postuserId;
    }

    public void setPostuserId(Long postuserId) {
        this.postuserId = postuserId;
    }

    public String getPostuserName() {
        return postuserName;
    }

    public void setPostuserName(String postuserName) {
        this.postuserName = postuserName;
    }

    public Long getAttentuserId() {
        return attentuserId;
    }

    public void setAttentuserId(Long attentuserId) {
        this.attentuserId = attentuserId;
    }

    @Override
    public String toString() {
        return "MyMessageDetailEntity{" +
                "id=" + id +
                ", msgId=" + msgId +
                ", userId=" + userId +
                ", readYn=" + readYn +
                ", postId=" + postId +
                ", postuserId=" + postuserId +
                ", postuserName='" + postuserName + '\'' +
                ", attentuserId=" + attentuserId +
                ", postPrefixContext='" + postPrefixContext + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}