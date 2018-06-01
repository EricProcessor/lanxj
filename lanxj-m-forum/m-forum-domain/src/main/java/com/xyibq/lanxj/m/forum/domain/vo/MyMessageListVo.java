package com.xyibq.lanxj.m.forum.domain.vo;

import java.io.Serializable;

public class MyMessageListVo implements Serializable{

    private static final long serialVersionUID = 1L;

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


    /**
     * 是否已读
     */
    private Long readYn;


    //每个模块对应的未读消息数量
    private int messagecount;

    public int getMessagecount() {
        return messagecount;
    }

    public void setMessagecount(int messagecount) {
        this.messagecount = messagecount;
    }

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

    public Long getReadYn() {
        return readYn;
    }

    public void setReadYn(Long readYn) {
        this.readYn = readYn;
    }
}
