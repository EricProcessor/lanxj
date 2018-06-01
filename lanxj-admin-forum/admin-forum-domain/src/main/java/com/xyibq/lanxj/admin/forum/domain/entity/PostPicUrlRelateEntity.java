package com.xyibq.lanxj.admin.forum.domain.entity;

import java.io.Serializable;

/**
 * post_pic_url_relate 实体类
 */
public class PostPicUrlRelateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 帖子ID
     */
    private Long postId;

    private int psotUrlOder;
    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 创建时间
     */
    private String  createTime;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPsotUrlOder() {
        return psotUrlOder;
    }

    public void setPsotUrlOder(int psotUrlOder) {
        this.psotUrlOder = psotUrlOder;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
