package com.xyibq.lanxj.m.forum.common.rabbitmq;

public enum RabbitmqTopicEnum {

    ADD_LIKE_TOPIC("新增点赞","addLike"),
    ADD_COMMENT_TOPIC("新增评论","addComment");

    private String topicDis;
    private String topicCode;

    RabbitmqTopicEnum(String topicDis, String topicCode) {
        this.topicDis = topicDis;
        this.topicCode = topicCode;
    }

    public String getTopicDis() {
        return topicDis;
    }

    public String getTopicCode() {
        return topicCode;
    }


}
