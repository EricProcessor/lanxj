package com.xyibq.lanxj.m.forum.Rabbitmq;

import com.alibaba.fastjson.JSON;
import com.xyibq.lanxj.m.forum.domain.entity.MyMessageDetailEntity;
import com.xyibq.lanxj.m.forum.service.MyMessageDetailService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RabbitListener(queues = "newMessageinvitecomment")
public class RabbitmqReceiverbyinviteComment {

    @Resource
    MyMessageDetailService myMessageDetailService;

    /**
     * 监听邀请评论
     */
    //使用@RabbitListener监听指定队列、指定exchange、指定routingKey的消息
    //同时@RabbitListener有建立队列、exchange、routingKey的功能
    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "newMessageinvitecomment" , durable = "true") ,
                    exchange = @Exchange(value = "MessgeExchange" , type = "topic" , durable = "true") ,
                    key = "addinvitecomment")
    )
    public void receiveMessage(String message) throws Exception {
//        Map map = JSON.parseObject(message , Map.class);
//        System.out.println(JSON.toJSON(">>>>>>>>>>>>>>>>>>>>>>:"+map));
        MyMessageDetailEntity entity = JSON.parseObject(message , MyMessageDetailEntity.class);
        System.out.println(JSON.toJSON("邀请评论>>>>>>>>>>>>>>>>>>>>>>:"+entity.toString()));
        myMessageDetailService.addMessageDetailInfo(entity);
    }
}
