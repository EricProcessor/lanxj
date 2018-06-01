package com.xyibq.lanxj.m.forum.mapper;


import com.xyibq.lanxj.m.forum.domain.vo.MyMessageListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MyMessageListMapper {


    /**
     *  查询我的消息列表--涉及详情表的是否已读 字段
     */
    public List<MyMessageListVo> selectMyMessageList();


    /**
     *  查询我的消息列表--涉及详情表的是否已读 字段
     */
    public List<MyMessageListVo> selectMyMessageListInfo();

}