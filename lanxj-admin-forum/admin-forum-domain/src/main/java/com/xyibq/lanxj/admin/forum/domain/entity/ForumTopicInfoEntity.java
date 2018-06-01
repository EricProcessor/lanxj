package com.xyibq.lanxj.admin.forum.domain.entity;

import java.io.Serializable;

/**
 * (论坛)版块 信息表
 */
public class ForumTopicInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 版块ID
     */
    private Integer topicId;

    /**
     * 版块名称
     */
    private String topicName;

    /**
     * 版块图标路径
     */
    private String iconUrl;

    /**
     * 版块状态（0关闭 1开启 ）
     */
    private Integer topicStatus;

    /**
     *  是否可匿名（0否 1是 ）
     */
    private Integer anonymityYn;

    /**
     * 是否审核(0否 1是)
     */
    private Integer checkYn;

    /**
     * 版块顺序
     */
    private Integer topicOrder;

    /**
     * 版块块点赞数量
     */
    private Integer topicLikesCount;

    /**
     * 版块评论数量统计
     */
    private Integer topicCommentsCount;

    /**
     * 版块流量数量
     */
    private Integer topicPageView;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     *  修改人
     */
    private String modifyUser;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(Integer topicStatus) {
        this.topicStatus = topicStatus;
    }

    public Integer getAnonymityYn() {
        return anonymityYn;
    }

    public void setAnonymityYn(Integer anonymityYn) {
        this.anonymityYn = anonymityYn;
    }

    public Integer getCheckYn() {
        return checkYn;
    }

    public void setCheckYn(Integer checkYn) {
        this.checkYn = checkYn;
    }

    public Integer getTopicOrder() {
        return topicOrder;
    }

    public void setTopicOrder(Integer topicOrder) {
        this.topicOrder = topicOrder;
    }

    public Integer getTopicLikesCount() {
        return topicLikesCount;
    }

    public void setTopicLikesCount(Integer topicLikesCount) {
        this.topicLikesCount = topicLikesCount;
    }

    public Integer getTopicCommentsCount() {
        return topicCommentsCount;
    }

    public void setTopicCommentsCount(Integer topicCommentsCount) {
        this.topicCommentsCount = topicCommentsCount;
    }

    public Integer getTopicPageView() {
        return topicPageView;
    }

    public void setTopicPageView(Integer topicPageView) {
        this.topicPageView = topicPageView;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
