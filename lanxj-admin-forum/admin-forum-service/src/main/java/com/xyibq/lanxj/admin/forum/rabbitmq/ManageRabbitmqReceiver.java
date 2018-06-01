package com.xyibq.lanxj.admin.forum.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@RabbitListener(queues = "McommentMessage")
public class ManageRabbitmqReceiver {

//    @Resource
//    MyMessageDetailService myMessageDetailService;

    /**
     * 监听点赞
     */
    //使用@RabbitListener监听指定队列、指定exchange、指定routingKey的消息
    //同时@RabbitListener有建立队列、exchange、routingKey的功能
    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "McommentMessage" , durable = "true") ,
                    exchange = @Exchange(value = "MessgeExchange" , type = "topic" , durable = "true") ,
                    key = "Maddcomment")
    )
    public void receiveMessage(String message) throws Exception {
        Map map = JSON.parseObject(message , Map.class);
        System.out.println(JSON.toJSON("管理后台>>>>>>>>>>>>>>>>>>>>>>:"+map));
       // MyMessageDetailEntity entity = JSON.parseObject(message , MyMessageDetailEntity.class);
        //System.out.println(JSON.toJSON(">>>>>>>>>>>>>>>>>>>>>>:"+entity.toString()));
      //  myMessageDetailService.addMessageDetailInfo(entity);
    }
}
