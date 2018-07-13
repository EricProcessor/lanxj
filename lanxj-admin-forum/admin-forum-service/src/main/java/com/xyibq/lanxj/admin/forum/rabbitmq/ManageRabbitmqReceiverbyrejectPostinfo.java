package com.xyibq.lanxj.admin.forum.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.xyibq.lanxj.admin.forum.domain.entity.MyMessageDetailEntity;
import com.xyibq.lanxj.admin.forum.service.MyMessageDetailService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@RabbitListener(queues = "MrejectPostinfoMessage")
public class ManageRabbitmqReceiverbyrejectPostinfo {

    @Resource
    MyMessageDetailService myMessageDetailService;

    /**
     * 监听拒绝帖子
     * 使用@RabbitListener监听指定队列、指定exchange、指定routingKey的消息
     * 同时@RabbitListener有建立队列、exchange、routingKey的功能
     */
    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "MrejectPostinfoMessage" , durable = "true") ,
                    exchange = @Exchange(value = "MessgeExchange" , type = "topic" , durable = "true") ,
                    key = "MrejectPostinfo")
    )
    public void receiveMessage(String message) throws Exception {
        MyMessageDetailEntity entity = JSON.parseObject(message , MyMessageDetailEntity.class);
        System.out.println(JSON.toJSON("管理后台拒绝帖子>>>>>>>>>>>>>>>>>>>>>>:"+entity.toString()));
        myMessageDetailService.addMessageDetailInfo(entity);
    }
}
