package com.xyibq.lanxj.m.forum.domain.entity;

import java.io.Serializable;

public class MyMessageListEntity implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 消息类型ID
     */
    private Long msgTypeId;

    /**
     * 消息类型名称
     */
    private String msgTypeName;

    /**
     * 消息模板
     */
    private String msgTemplate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMsgTypeId() {
        return msgTypeId;
    }

    public void setMsgTypeId(Long msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }

    public String getMsgTemplate() {
        return msgTemplate;
    }

    public void setMsgTemplate(String msgTemplate) {
        this.msgTemplate = msgTemplate;
    }

}
