package com.xyibq.lanxj.m.forum.Rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xyibq.lanxj.m.forum.domain.entity.MyMessageDetailEntity;
import com.xyibq.lanxj.m.forum.service.MyMessageDetailService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@RabbitListener(queues = "newMessageaddcommentrelate")
public class RabbitmqReceiverbypostCommentRelate {

    @Resource
    MyMessageDetailService myMessageDetailService;

    /**
     * 监听评论关联
     */
    //使用@RabbitListener监听指定队列、指定exchange、指定routingKey的消息
    //同时@RabbitListener有建立队列、exchange、routingKey的功能
    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "newMessageaddcommentrelate" , durable = "true") ,
                    exchange = @Exchange(value = "MessgeExchange" , type = "topic" , durable = "true") ,
                    key = "addcommentrelate")
    )
    public void receiveMessage(String message) throws Exception {
        //json 转list
        List<MyMessageDetailEntity> list = JSONObject.parseArray(message, MyMessageDetailEntity.class);
        System.out.println(list.size());
        System.out.println(JSON.toJSON("评论关联>>>>>>>>>>>>>>>>>>>>>>:"+list.toString()));
        for(MyMessageDetailEntity entity:list){
            myMessageDetailService.addMessageDetailInfo(entity);
        }
    }
}
